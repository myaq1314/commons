package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author : czh
 * description :
 * date : 2021-06-01
 * email 916419307@qq.com
 */
public final class FileReadUtil {

    public static String readLastLine(@NotBlankTag String path) {
        return readLastLine(path, StandardCharsets.UTF_8.name());
    }

    public static String readLastLine(@NotBlankTag String path, String charsetName) {
        byte[] lineBytes = readLastLineBytes(path);
        try {
            if (EmptyValidate.isNotBlank(charsetName)) {
                return new String(lineBytes, charsetName);
            } else {
                return new String(lineBytes);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("未知的编码方式");
        }
    }

    public static String matchReadByLast(@NotBlankTag String path, @NotBlankTag String match) {
        EmptyAssert.isNotBlank(match);

        File file = FileUtil.readFile(path);
        try (RandomAccessFile raFile = new RandomAccessFile(file, "r")) {
            long length = raFile.length();
            if (length == 0L) {
                throw new RuntimeException("没有可读取的数据");
            }

            // 排除空行
            long pos = length - 1;
            while (pos > 0) {
                while (pos > 0) {
                    pos--;
                    raFile.seek(pos);
                    if (raFile.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raFile.seek(0);
                }
                byte[] bytes = new byte[(int) (length - pos)];
                raFile.read(bytes);
                String line = new String(bytes);
                if (line.contains(match)) {
                    return line;
                }
                length = pos;
                pos--;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败");
        }
    }

    public static byte[] readLastLineBytes(@NotBlankTag String path) {
        File file = FileUtil.readFile(path);

        try (RandomAccessFile raFile = new RandomAccessFile(file, "r")) {
            long length = raFile.length();
            if (length == 0L) {
                throw new RuntimeException("没有可读取的数据");
            }

            // 排除空行
            long pos = length - 1;
            while (pos > 0) {
                pos--;
                raFile.seek(pos);
                if (raFile.readByte() == '\n') {
                    break;
                }
            }
            if (pos == 0) {
                raFile.seek(0);
            }
            byte[] bytes = new byte[(int) (length - pos)];
            raFile.read(bytes);
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败");
        }
    }
}
