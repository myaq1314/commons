package org.czh.commons.validate;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;

import java.io.File;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
public final class FileValidate {

    public static File isExistsFile(@NotBlankTag final String filePath) {
        EmptyAssert.isNotBlank(filePath);

        File file = new File(filePath);
        return file.exists() && file.isFile() ? file : null;
    }

    public static File isReadFile(@NotBlankTag final String filePath) {
        File file = isExistsFile(filePath);
        return EmptyValidate.isNotNull(file) && file.canRead() ? file : null;
    }

    public static File isWriteFile(@NotBlankTag final String filePath) {
        File file = isReadFile(filePath);
        return EmptyValidate.isNotNull(file) && file.canWrite() ? file : null;
    }

    public static File isExecuteFile(@NotBlankTag final String filePath) {
        File file = isReadFile(filePath);
        return EmptyValidate.isNotNull(file) && file.canExecute() ? file : null;
    }

    public static File isExistsDirectory(@NotBlankTag final String directoryPath) {
        EmptyAssert.isNotBlank(directoryPath);

        File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory() ? directory : null;
    }

    public static File isReadDirectory(@NotBlankTag final String directoryPath) {
        File directory = isExistsDirectory(directoryPath);
        return EmptyValidate.isNotNull(directory) && directory.canRead() ? directory : null;
    }

    public static File isWriteDirectory(@NotBlankTag final String directoryPath) {
        File directory = isReadDirectory(directoryPath);
        return EmptyValidate.isNotNull(directory) && directory.canWrite() ? directory : null;
    }

    public static boolean isExistsFile(@NotNullTag final File file) {
        EmptyAssert.isNotNull(file);
        return file.exists() && file.isFile();
    }

    public static boolean isReadFile(@NotNullTag final File file) {
        return isExistsFile(file) && file.canRead();
    }

    public static boolean isWriteFile(@NotNullTag final File file) {
        return isReadFile(file) && file.canWrite();
    }

    public static boolean isExecuteFile(@NotNullTag final File file) {
        return isReadFile(file) && file.canExecute();
    }

    public static boolean isExistsDirectory(@NotNullTag final File directory) {
        EmptyAssert.isNotNull(directory);
        return directory.exists() && directory.isDirectory();
    }

    public static boolean isReadDirectory(@NotNullTag final File directory) {
        return isExistsDirectory(directory) && directory.canRead();
    }

    public static boolean isWriteDirectory(@NotNullTag final File directory) {
        return isReadDirectory(directory) && directory.canWrite();
    }
}
