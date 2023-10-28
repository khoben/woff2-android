cmake_minimum_required(VERSION 2.8.6)

# disable pkgconfig searching libraries, all libraries are static imported
set(CMAKE_DISABLE_FIND_PACKAGE_PkgConfig ON)
# Workaround error of `Unknown CMake command "pkg_check_modules".'`
# refer to: https://gitlab.kitware.com/cmake/cmake/-/issues/17043
macro( pkg_check_modules )
endmacro()

add_subdirectory(sources/woff2)