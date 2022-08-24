# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

project("woff2decoder")

add_library(
        brotlicommon
        STATIC
        IMPORTED)
set_target_properties(
        brotlicommon PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/brotli/${ANDROID_ABI}/lib/libbrotlicommon-static.a)

add_library(
        brotlidec
        STATIC
        IMPORTED)
set_target_properties(
        brotlidec PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/brotli/${ANDROID_ABI}/lib/libbrotlidec-static.a)

add_library(woff2common
        STATIC
        IMPORTED)
set_target_properties(woff2common PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/lib/libwoff2common.a
        INTERFACE_INCLUDE_DIRECTORIES ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/include)

add_library(woff2dec
        STATIC
        IMPORTED)
set_target_properties(woff2dec PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/lib/libwoff2dec.a
        INTERFACE_INCLUDE_DIRECTORIES ${CMAKE_SOURCE_DIR}/thirdparty/woff2/${ANDROID_ABI}/include)

add_library(
        woff2decoder
        SHARED
        woff2decoder.cpp)
find_library(log-lib log)
target_link_libraries(woff2decoder woff2dec woff2common brotlidec brotlicommon ${log-lib})