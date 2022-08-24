package com.github.khoben.libwoff2dec

/**
 * Native woff2 decoder
 */
object Woff2Decoder {

    init {
        System.loadLibrary("woff2decoder")
    }

    /**
     * Decompress woff2 font
     *
     * @param inPath source woff2 font path
     * @param outPath output decompressed font path
     * @return Returns true is success
     */
    external fun decodeFile(inPath: String, outPath: String): Boolean

    /**
     * Decompress woff2 font
     *
     * @param inBytes source woff2 font bytes
     * @return Returns tff font bytes or null if there is a decompression error
     */
    external fun decodeBytes(inBytes: ByteArray): ByteArray?
}