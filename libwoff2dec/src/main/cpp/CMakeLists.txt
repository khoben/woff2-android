# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

project("libwoff2dec")

add_library(
        brotlicommon
        SHARED
        IMPORTED)
set_target_properties(
        brotlicommon PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/brotli/${ANDROID_ABI}/lib/libbrotlicommon.so)

add_library(
        brotlidec
        SHARED
        IMPORTED)
set_target_properties(
        brotlidec PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/brotli/${ANDROID_ABI}/lib/libbrotlidec.so)


add_library(woff2common
        SHARED
        IMPORTED)
set_target_properties(woff2common PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/lib/libwoff2common.so
        INTERFACE_INCLUDE_DIRECTORIES ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/include)

add_library(woff2dec
        SHARED
        IMPORTED)
set_target_properties(woff2dec PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/lib/libwoff2dec.so
        INTERFACE_INCLUDE_DIRECTORIES ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/include)

add_library(
        libwoff2dec
        SHARED
        libwoff2.cpp)
find_library(log-lib log)
target_link_libraries(libwoff2dec brotlicommon brotlidec woff2common woff2dec ${log-lib})