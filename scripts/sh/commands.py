import os
import shutil
import fnmatch
import subprocess


def prepend_env(key: str, value: str) -> None:
    os.environ[key] = value + os.pathsep + os.environ[key]


def export(key: str, value: str) -> None:
    os.putenv(key, value)


def run(command: str) -> None:
    process = subprocess.Popen(
        command.split(" "),
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        encoding="utf8",
    )
    process_name = os.path.basename(process.args[0])

    for line in process.stdout:
        print(f"[{process_name}] {line.rstrip()}")

    if process.wait() != 0:
        raise RuntimeError(
            f'Unsuccessful command: {process_name} {" ".join(process.args[1:])}'
        )


def mkdirs(path: str) -> None:
    os.makedirs(path, exist_ok=True)


def cp(src: str, dst: str) -> None:
    shutil.copyfile(src, dst)


def cp_tree(src: str, dst: str, *include_mask) -> None:
    def include_patterns(*patterns):
        def _ignore_patterns(path, names):
            keep = set(
                name for pattern in patterns for name in fnmatch.filter(names, pattern)
            )
            ignore = set(
                name
                for name in names
                if name not in keep and not os.path.isdir(os.path.join(path, name))
            )
            return ignore

        return _ignore_patterns

    shutil.copytree(
        src,
        dst,
        ignore=include_patterns(*include_mask) if include_mask else None,
        dirs_exist_ok=True,
    )


def rm(path: str) -> None:
    def onerror(func, path, exc_info) -> None:
        import stat

        if not os.access(path, os.W_OK):
            os.chmod(path, stat.S_IWUSR)
            func(path)
        else:
            raise

    if os.path.exists(path):
        if os.path.isdir(path):
            shutil.rmtree(path, onerror=onerror)
        elif os.path.isfile(path) or os.path.islink(path):
            os.remove(path)
