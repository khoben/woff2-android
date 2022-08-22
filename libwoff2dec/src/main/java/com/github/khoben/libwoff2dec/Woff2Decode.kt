package com.github.khoben.libwoff2dec

object Woff2Decode {

    init {
        System.loadLibrary("libwoff2dec")
    }

    /**
     * Decompress woff2 font
     *
     * @param inPath source woff2 font path
     * @param outPath output decompressed font path
     * @return Returns true is success
     */
    external fun decodeWOFF2(inPath: String, outPath: String): Boolean

    /**
     * Decompress woff2 font
     *
     * @param inBytes source woff2 font bytes
     * @return Returns tff font bytes
     */
    external fun decodeWOFF2Byte(inBytes: ByteArray): ByteArray
}