"""
Build and move to cpp folder:
    - brotli
    - woff2
"""
import os
from sh import run, cp_tree, rm, mkdirs, cd, prepend_env
from typing import Dict, List

CWD: str = os.path.dirname(os.path.realpath(__file__))

with open(os.path.join(CWD, 'build_ndk.properties'), 'r') as f:
    env_vars: Dict[str, str] = dict(
        tuple(line.rstrip().split('='))
        for line in f.readlines() if not line.startswith('#')
    )

ROOT_SOURCES_DIR: str = os.path.join(CWD, 'sources')
ROOT_INSTALL_DIR: str = os.path.join(ROOT_SOURCES_DIR, 'install')
TARGET_DIR: str = os.path.join(os.path.dirname(CWD), 'libwoff2dec', 'src', 'main', 'cpp', 'thirdparty')

WOFF2_REPO: str = env_vars['WOFF2_REPO']
WOFF2_VERSION: str = env_vars['WOFF2_VERSION']

ANDROID_SDK: str = env_vars['ANDROID_SDK']
NDK_ROOT: str = os.path.join(ANDROID_SDK, 'ndk', env_vars['NDK_VERSION'])
CMAKE_ROOT: str = os.path.join(ANDROID_SDK, 'cmake', env_vars['CMAKE_VERSION'], 'bin')
ABI_LIST: List[str] = ["armeabi-v7a", "arm64-v8a", "x86", "x86_64"]
MIN_ANDROID_SDK: str = env_vars['MIN_ANDROID_SDK']

CPU_COUNT: int = os.cpu_count()

if not os.path.exists(NDK_ROOT):
    raise ValueError(
        "NDK_ROOT points to invalid path. "
        "Сheck that all variables are set correctly in build_ndk.properties"
    )

print(f'ANDROID_SDK={ANDROID_SDK}')
print(f'NDK_ROOT={NDK_ROOT}')
print(f'CMAKE_ROOT={CMAKE_ROOT}')

def init_env() -> None:
    print('Initialize environment...')
    prepend_env('PATH', CMAKE_ROOT)

def clear_all() -> None:
    print('Clearing...')
    rm(ROOT_SOURCES_DIR)
    rm(TARGET_DIR)


def fetch_woff2_sources() -> None:
    print(f'Fetching woff2@{WOFF2_VERSION} from {WOFF2_REPO}...')

    mkdirs(ROOT_SOURCES_DIR)
    cd(ROOT_SOURCES_DIR)

    run(
        f'git clone --single-branch --branch {WOFF2_VERSION} --recursive {WOFF2_REPO}')

    print('woff2 has been fetched')


def build_woff2() -> None:
    print('Building woff2...')

    WOFF2_SOURCE_DIR = os.path.join(ROOT_SOURCES_DIR, 'woff2')

    for ABI in ABI_LIST:

        print(f'[{ABI}] Building brotli...')

        BROTLI_SOURCE_DIR = os.path.join(WOFF2_SOURCE_DIR, 'brotli')
        BROTLI_PREFIX_PATH = os.path.join(ROOT_INSTALL_DIR, 'brotli', ABI)
        BROTLI_BUILD_PATH = os.path.join(BROTLI_SOURCE_DIR, 'out', ABI)

        run(f'cmake -S {BROTLI_SOURCE_DIR} -B {BROTLI_BUILD_PATH}'
            f' -DCMAKE_INSTALL_PREFIX={BROTLI_PREFIX_PATH} -DCMAKE_BUILD_TYPE=Release'
            f' -DCMAKE_TOOLCHAIN_FILE={NDK_ROOT}/build/cmake/android.toolchain.cmake'
            f' -DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON'
            f' -DANDROID_ABI={ABI} -DANDROID_NATIVE_API_LEVEL={MIN_ANDROID_SDK} -G Ninja')
        
        run(f'cmake --build {BROTLI_BUILD_PATH} --config Release --target install -j {CPU_COUNT}')

        BROTLI_INCLUDE_DIR = os.path.join(BROTLI_PREFIX_PATH, 'include')
        BROTLI_LIB_DIR = os.path.join(BROTLI_PREFIX_PATH, 'lib')

        print(f'[{ABI}] Building woff2')

        WOFF2_PREFIX_PATH = os.path.join(ROOT_INSTALL_DIR, 'woff2', ABI)
        WOFF2_BUILD_PATH = os.path.join(WOFF2_SOURCE_DIR, 'out', ABI)

        run(f'cmake -S {WOFF2_SOURCE_DIR} -B {WOFF2_BUILD_PATH}'
            f' -DBUILD_SHARED_LIBS=OFF'
            f' -DCMAKE_INSTALL_PREFIX={WOFF2_PREFIX_PATH} -DCMAKE_BUILD_TYPE=RELEASE'
            f' -DBROTLIDEC_INCLUDE_DIRS={BROTLI_INCLUDE_DIR} -DBROTLIDEC_LIBRARIES={BROTLI_LIB_DIR}/libbrotlidec.so'
            f' -DBROTLIENC_INCLUDE_DIRS={BROTLI_INCLUDE_DIR} -DBROTLIENC_LIBRARIES={BROTLI_LIB_DIR}/libbrotlienc.so'
            f' -DCMAKE_TOOLCHAIN_FILE={NDK_ROOT}/build/cmake/android.toolchain.cmake -DANDROID_ABI={ABI}'
            f' -DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON'
            f' -DANDROID_NATIVE_API_LEVEL={MIN_ANDROID_SDK} -G Ninja')

        run(f'cmake --build {WOFF2_BUILD_PATH} --config Release --target install -j {CPU_COUNT}')

        print(f'[{ABI}] woff2 has been built', end='\n\n')

    print(f'{ABI_LIST} woff2 building finished...')


def copy_libs_to_target() -> None:
    rm(TARGET_DIR)
    cp_tree(
        ROOT_INSTALL_DIR, TARGET_DIR,
        r'*.h', 
        'libbrotlicommon-static.a', 'libbrotlidec-static.a',
        'libwoff2common.a', 'libwoff2dec.a'
    )

    print('Libs copied to target')


if __name__ == '__main__':
    print("""
    Build and move to cpp folder:
        - brotli
        - woff2
    """)
    init_env()
    clear_all()
    fetch_woff2_sources()
    build_woff2()
    copy_libs_to_target()
    print("Finished successfully")
