package org.czh.commons.validate;

import java.io.File;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
public final class FileValidate {

    public static File isExistsFile(final String filePath) {
        EmptyAssert.isNotBlank(filePath);

        File file = new File(filePath);
        return file.exists() && file.isFile() ? file : null;
    }

    public static File isReadFile(final String filePath) {
        File file = isExistsFile(filePath);
        return EmptyValidate.isNotNull(file) && file.canRead() ? file : null;
    }

    public static File isWriteFile(final String filePath) {
        File file = isReadFile(filePath);
        return EmptyValidate.isNotNull(file) && file.canWrite() ? file : null;
    }

    public static File isExecuteFile(final String filePath) {
        File file = isReadFile(filePath);
        return EmptyValidate.isNotNull(file) && file.canExecute() ? file : null;
    }

    public static File isExistsDirectory(final String directoryPath) {
        EmptyAssert.isNotBlank(directoryPath);

        File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory() ? directory : null;
    }

    public static File isReadDirectory(final String directoryPath) {
        File directory = isExistsDirectory(directoryPath);
        return EmptyValidate.isNotNull(directory) && directory.canRead() ? directory : null;
    }

    public static File isWriteDirectory(final String directoryPath) {
        File directory = isReadDirectory(directoryPath);
        return EmptyValidate.isNotNull(directory) && directory.canWrite() ? directory : null;
    }

    public static boolean isExistsFile(final File file) {
        EmptyAssert.isNotNull(file);
        return file.exists() && file.isFile();
    }

    public static boolean isReadFile(final File file) {
        return isExistsFile(file) && file.canRead();
    }

    public static boolean isWriteFile(final File file) {
        return isReadFile(file) && file.canWrite();
    }

    public static boolean isExecuteFile(final File file) {
        return isReadFile(file) && file.canExecute();
    }

    public static boolean isExistsDirectory(final File directory) {
        EmptyAssert.isNotNull(directory);
        return directory.exists() && directory.isDirectory();
    }

    public static boolean isReadDirectory(final File directory) {
        return isExistsDirectory(directory) && directory.canRead();
    }

    public static boolean isWriteDirectory(final File directory) {
        return isReadDirectory(directory) && directory.canWrite();
    }
}
