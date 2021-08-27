package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.EncodeAssert;
import org.czh.commons.validate.FileAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class PropertyUtil {

    private static final Map<String, String> propertyMap = new HashMap<>();

    static {
        try {
            List<String> filePathList = new ArrayList<>();
            findFiles(PropertyUtil.class.getResource("/").getPath(), "*.properties", filePathList);
            if (EmptyValidate.isNotEmpty(filePathList)) {
                for (String filePath : filePathList) {
                    load(filePath);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("加载配置文件异常");
        }
    }

    public static void main(String[] args) {
        System.out.println(propertyMap);
    }

    /**
     * 从上下文中获取参数value
     */
    public static String getProperty(@NotBlankTag String propertiesKey) {
        EmptyAssert.isNotBlank(propertiesKey);
        return propertyMap.get(propertiesKey);
    }

    /**
     * 获取所有属性名称
     */
    public static List<String> getPropertyNames() {
        Set<String> keySet = propertyMap.keySet();
        return new ArrayList<>(keySet);
    }

    /**
     * 获取所有属性
     */
    public static List<Map.Entry<String, String>> getProperties() {
        Set<Map.Entry<String, String>> entrySet = propertyMap.entrySet();
        return new ArrayList<>(entrySet);
    }

    /**
     * 递归查找文件
     *
     * @param filePath       查找的文件夹路径
     * @param targetFileName 需要查找的文件名
     * @param filePathList   查找到的文件集合
     */
    private static void findFiles(String filePath, String targetFileName, List<String> filePathList) {
        File root = new File(filePath);
        File[] fileArray = root.listFiles();
        if (EmptyValidate.isEmpty(fileArray)) {
            return;
        }
        for (File file : fileArray) {
            if (file.isDirectory()) { // 文件夹
                findFiles(file.getAbsolutePath(), targetFileName, filePathList); // 递归
                continue;
            }
            // 文件
            if (wildcardMatch(targetFileName, file.getName())) { // 通配符匹配
                filePathList.add(file.getAbsolutePath());
            }
        }
    }

    /**
     * 通配符匹配
     *
     * @param pattern 通配符模式
     * @param str     待匹配的字符串
     * @return 匹配成功则返回true，否则返回false
     */
    private static boolean wildcardMatch(String pattern, String str) {
        int patternLength = pattern.length();
        int strLength = str.length();
        int strIndex = 0;
        char ch;
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
            ch = pattern.charAt(patternIndex);
            if (ch == '*') {
                //通配符星号*表示可以匹配任意多个字符
                while (strIndex < strLength) {
                    if (wildcardMatch(pattern.substring(patternIndex + 1),
                            str.substring(strIndex))) {
                        return true;
                    }
                    strIndex++;
                }
            } else if (ch == '?') {
                //通配符问号?表示匹配任意一个字符
                strIndex++;
                if (strIndex > strLength) {
                    //表示str中已经没有字符匹配?了。
                    return false;
                }
            } else {
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
                    return false;
                }
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }

    private static synchronized void load(String filePath) throws IOException {
        FileInputStream fis = null;
        try {
            File file = FileAssert.isReadFile(filePath);
            EncodeAssert.isUtf8(file);
            fis = new FileInputStream(file);
            load0(new LineReader(fis));
        } finally {
            StreamUtil.close(fis);
        }
    }

    private static void load0(LineReader lr) throws IOException {
        char[] convertBuf = new char[1024];
        int limit;
        int keyLen;
        int valueStart;
        char c;
        boolean hasSep;
        boolean precedingBackslash;

        while ((limit = lr.readLine()) >= 0) {
            keyLen = 0;
            valueStart = limit;
            hasSep = false;

            //System.out.println("line=<" + new String(lineBuf, 0, limit) + ">");
            precedingBackslash = false;
            while (keyLen < limit) {
                c = lr.lineBuf[keyLen];
                //need check if escaped.
                if ((c == '=' || c == ':') && !precedingBackslash) {
                    valueStart = keyLen + 1;
                    hasSep = true;
                    break;
                } else if ((c == ' ' || c == '\t' || c == '\f') && !precedingBackslash) {
                    valueStart = keyLen + 1;
                    break;
                }
                if (c == '\\') {
                    precedingBackslash = !precedingBackslash;
                } else {
                    precedingBackslash = false;
                }
                keyLen++;
            }
            while (valueStart < limit) {
                c = lr.lineBuf[valueStart];
                if (c != ' ' && c != '\t' && c != '\f') {
                    if (!hasSep && (c == '=' || c == ':')) {
                        hasSep = true;
                    } else {
                        break;
                    }
                }
                valueStart++;
            }
            String key = loadConvert(lr.lineBuf, 0, keyLen, convertBuf);
            EmptyAssert.isNotEmpty(key);
            String value = loadConvert(lr.lineBuf, valueStart, limit - valueStart, convertBuf);
            propertyMap.put(key, value);
        }
    }

    /*
     * Converts encoded &#92;uxxxx to unicode chars
     * and changes special saved chars to their original forms
     */
    private static String loadConvert(char[] in, int off, int len, char[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') aChar = '\t';
                    else if (aChar == 'r') aChar = '\r';
                    else if (aChar == 'n') aChar = '\n';
                    else if (aChar == 'f') aChar = '\f';
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    static class LineReader {
        byte[] inByteBuf;
        char[] inCharBuf;
        char[] lineBuf = new char[1024];
        int inLimit = 0;
        int inOff = 0;
        InputStream inStream;
        Reader reader;

        LineReader(InputStream inStream) {
            this.inStream = inStream;
            inByteBuf = new byte[8192];
        }

        int readLine() throws IOException {
            int len = 0;
            char c;

            boolean skipWhiteSpace = true;
            boolean isCommentLine = false;
            boolean isNewLine = true;
            boolean appendedLineBegin = false;
            boolean precedingBackslash = false;
            boolean skipLF = false;

            while (true) {
                if (inOff >= inLimit) {
                    inLimit = (inStream == null) ? reader.read(inCharBuf)
                            : inStream.read(inByteBuf);
                    inOff = 0;
                    if (inLimit <= 0) {
                        if (len == 0 || isCommentLine) {
                            return -1;
                        }
                        if (precedingBackslash) {
                            len--;
                        }
                        return len;
                    }
                }
                if (inStream != null) {
                    //The line below is equivalent to calling a
                    //ISO8859-1 decoder.
                    c = (char) (0xff & inByteBuf[inOff++]);
                } else {
                    c = inCharBuf[inOff++];
                }
                if (skipLF) {
                    skipLF = false;
                    if (c == '\n') {
                        continue;
                    }
                }
                if (skipWhiteSpace) {
                    if (c == ' ' || c == '\t' || c == '\f') {
                        continue;
                    }
                    if (!appendedLineBegin && (c == '\r' || c == '\n')) {
                        continue;
                    }
                    skipWhiteSpace = false;
                    appendedLineBegin = false;
                }
                if (isNewLine) {
                    isNewLine = false;
                    if (c == '#' || c == '!') {
                        isCommentLine = true;
                        continue;
                    }
                }

                if (c != '\n' && c != '\r') {
                    lineBuf[len++] = c;
                    if (len == lineBuf.length) {
                        int newLength = lineBuf.length * 2;
                        if (newLength < 0) {
                            newLength = Integer.MAX_VALUE;
                        }
                        char[] buf = new char[newLength];
                        System.arraycopy(lineBuf, 0, buf, 0, lineBuf.length);
                        lineBuf = buf;
                    }
                    //flip the preceding backslash flag
                    if (c == '\\') {
                        precedingBackslash = !precedingBackslash;
                    } else {
                        precedingBackslash = false;
                    }
                } else {
                    // reached EOL
                    if (isCommentLine || len == 0) {
                        isCommentLine = false;
                        isNewLine = true;
                        skipWhiteSpace = true;
                        len = 0;
                        continue;
                    }
                    if (inOff >= inLimit) {
                        inLimit = (inStream == null)
                                ? reader.read(inCharBuf)
                                : inStream.read(inByteBuf);
                        inOff = 0;
                        if (inLimit <= 0) {
                            if (precedingBackslash) {
                                len--;
                            }
                            return len;
                        }
                    }
                    if (precedingBackslash) {
                        len -= 1;
                        //skip the leading whitespace characters in following line
                        skipWhiteSpace = true;
                        appendedLineBegin = true;
                        precedingBackslash = false;
                        if (c == '\r') {
                            skipLF = true;
                        }
                    } else {
                        return len;
                    }
                }
            }
        }
    }
}
