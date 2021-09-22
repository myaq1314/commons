package org.czh.commons.validate;

import java.io.File;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class FileAssert {

    public static File isExistsFile(final String filePath) {
        return isExistsFile(filePath, "[Assertion failed] - The file must exist and be a file");
    }

    public static File isExistsFile(final String filePath, final String message) {
        File file = FileValidate.isExistsFile(filePath);
        EmptyAssert.isNotNull(file, message);
        return file;
    }

    public static File isReadFile(final String filePath) {
        return isReadFile(filePath, "[Assertion failed] - The file must exist and be a readable file");
    }

    public static File isReadFile(final String filePath, final String message) {
        File file = FileValidate.isReadFile(filePath);
        EmptyAssert.isNotNull(file, message);
        return file;
    }

    public static File isWriteFile(final String filePath) {
        return isWriteFile(filePath, "[Assertion failed] - The file must exist and be a writable file");
    }

    public static File isWriteFile(final String filePath, final String message) {
        File file = FileValidate.isWriteFile(filePath);
        EmptyAssert.isNotNull(file, message);
        return file;
    }

    public static File isExecuteFile(final String filePath) {
        return isExecuteFile(filePath, "[Assertion failed] - The file must exist and be a executable file");
    }

    public static File isExecuteFile(final String filePath, final String message) {
        File file = FileValidate.isExecuteFile(filePath);
        EmptyAssert.isNotNull(file, message);
        return file;
    }

    public static File isExistsDirectory(final String filePath) {
        return isExistsDirectory(filePath, "[Assertion failed] - The file must exist and be a directory");
    }

    public static File isExistsDirectory(final String filePath, final String message) {
        File directory = FileValidate.isExistsDirectory(filePath);
        EmptyAssert.isNotNull(directory, message);
        return directory;
    }

    public static File isReadDirectory(final String filePath) {
        return isReadDirectory(filePath, "[Assertion failed] - The file must exist and be a readable directory");
    }

    public static File isReadDirectory(final String filePath, final String message) {
        File directory = FileValidate.isReadDirectory(filePath);
        EmptyAssert.isNotNull(directory, message);
        return directory;
    }

    public static File isWriteDirectory(final String filePath) {
        return isWriteDirectory(filePath, "[Assertion failed] - The file must exist and be a writable directory");
    }

    public static File isWriteDirectory(final String filePath, final String message) {
        File directory = FileValidate.isWriteDirectory(filePath);
        EmptyAssert.isNotNull(directory, message);
        return directory;
    }

    public static void isExistsFile(final File file) {
        isExistsFile(file, "[Assertion failed] - The file must exist and be a file");
    }

    public static void isExistsFile(final File file, final String message) {
        if (!FileValidate.isExistsFile(file)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isReadFile(final File file) {
        isReadFile(file, "[Assertion failed] - The file must exist and be a readable file");
    }

    public static void isReadFile(final File file, final String message) {
        if (!FileValidate.isReadFile(file)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isWriteFile(final File file) {
        isWriteFile(file, "[Assertion failed] - The file must exist and be a writable file");
    }

    public static void isWriteFile(final File file, final String message) {
        if (!FileValidate.isWriteFile(file)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isExecuteFile(final File file) {
        isExecuteFile(file, "[Assertion failed] - The file must exist and be a executable file");
    }

    public static void isExecuteFile(final File file, final String message) {
        if (!FileValidate.isExecuteFile(file)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isExistsDirectory(final File directory) {
        isExistsDirectory(directory, "[Assertion failed] - The file must exist and be a directory");
    }

    public static void isExistsDirectory(final File directory, final String message) {
        if (!FileValidate.isExistsDirectory(directory)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isReadDirectory(final File directory) {
        isReadDirectory(directory, "[Assertion failed] - The file must exist and be a readable directory");
    }

    public static void isReadDirectory(final File directory, final String message) {
        if (!FileValidate.isReadDirectory(directory)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isWriteDirectory(final File directory) {
        isWriteDirectory(directory, "[Assertion failed] - The file must exist and be a writable directory");
    }

    public static void isWriteDirectory(final File directory, final String message) {
        if (!FileValidate.isWriteDirectory(directory)) {
            throw new IllegalStateException(message);
        }
    }
}
