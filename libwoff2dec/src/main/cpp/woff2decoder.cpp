#include <jni.h>
#include <string>

#include <fstream>
#include <iterator>
#include <woff2/decode.h>

#include <android/log.h>

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_github_khoben_libwoff2dec_Woff2Decoder_decodeFile(JNIEnv *env, jobject thiz,
                                                                                               jstring input_file_name,
                                                                                               jstring output_file_name) {

    const char *in_file = env->GetStringUTFChars(input_file_name, nullptr);
    const char *out_file = env->GetStringUTFChars(output_file_name, nullptr);

    std::string in_filename(in_file);
    std::string out_filename(out_file);

    std::ifstream ifs(in_filename.c_str(), std::ios::binary);
    std::string input = std::string(std::istreambuf_iterator<char>(ifs.rdbuf()),
                                    std::istreambuf_iterator<char>());
    const auto *raw_input = reinterpret_cast<const uint8_t *>(input.data());

    std::string output(
            std::min(woff2::ComputeWOFF2FinalSize(raw_input, input.size()),
                     woff2::kDefaultMaxSize), 0);

    woff2::WOFF2StringOut out(&output);

    if (woff2::ConvertWOFF2ToTTF(raw_input, input.size(), &out)) {
        std::ofstream ofs(out_filename.c_str(), std::ios::binary);
        std::copy(output.begin(),
                  output.begin() + (int) out.Size(),
                  std::ostream_iterator<char>(ofs));
        return true;
    }

    return false;
}


extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_github_khoben_libwoff2dec_Woff2Decoder_decodeBytes(JNIEnv *env, jobject thiz,
                                                            jbyteArray input_bytes) {

    const auto *input_data = (uint8_t *) (env->GetByteArrayElements(input_bytes, nullptr));
    jsize input_data_len = env->GetArrayLength(input_bytes);

    std::string output(
            std::min(woff2::ComputeWOFF2FinalSize(input_data, input_data_len),
                     woff2::kDefaultMaxSize),
            0);
    const auto *output_data = (uint8_t *) (&output[0]);

    woff2::WOFF2StringOut out(&output);

    if (!woff2::ConvertWOFF2ToTTF(input_data, input_data_len, &out)) {
        __android_log_print(ANDROID_LOG_ERROR, "libwoff2dec", "Error while decompressing");
        env->ReleaseByteArrayElements(input_bytes, (jbyte *) (input_data), 0);
        return nullptr;
    }

    output.resize(out.Size());

    jbyteArray arr = env->NewByteArray((jsize) out.Size());
    env->SetByteArrayRegion(arr, 0, (jsize) out.Size(), (jbyte *) (output_data));

    env->ReleaseByteArrayElements(input_bytes, (jbyte *) (input_data), 0);

    return arr;
}