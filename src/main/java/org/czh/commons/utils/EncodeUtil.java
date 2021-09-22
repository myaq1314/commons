package org.czh.commons.utils;

import org.czh.commons.constant.EncodeConstant;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FileAssert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * @author : czh
 * description : 自动识别文本、文件编码格式
 * date : 2021/8/27
 * email 916419307@qq.com
 */
public final class EncodeUtil {

    public static String getFileEncode(String filePath) {
        EmptyAssert.isNotBlank(filePath);
        return getFileEncode(new File(filePath));
    }

    public static String getFileEncode(File file) {
        FileAssert.isReadFile(file);
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));

            // 简单判断
            bis.mark(0);
            byte[] simpleTags = new byte[3];
            int len = bis.read(simpleTags);
            if (len >= 2 && simpleTags[0] == -1 && simpleTags[1] == -2) {
                return EncodeConstant.UTF16();
            } else if (len >= 2 && simpleTags[0] == -2 && simpleTags[1] == -1) {
                return EncodeConstant.UNICODE();
            } else if (len >= 3 && simpleTags[0] == -17 && simpleTags[1] == -69 && simpleTags[2] == -65) {
                return EncodeConstant.UTF8();
            }
            // 复杂判断
            return isUTF8(bis) ? EncodeConstant.UTF8() : EncodeConstant.GBK();
        } catch (IOException e) {
            throw new RuntimeException("获取文件编码格式异常");
        } finally {
            StreamUtil.close(bis);
        }
    }

    private static boolean isUTF8(BufferedInputStream bis) throws IOException {
        bis.reset();

        int code;
        while ((code = bis.read()) != -1) { // 开始位置字节
            BitSet startBitSet = convert2BitSet(code);
            if (startBitSet.get(0)) {
                int otherCount = getCountOfSequential(startBitSet) - 1; // 理想中剩余字节数量
                byte[] otherBytes = new byte[otherCount]; // 剩余字节暂存的缓冲数组
                int otherLen = bis.read(otherBytes); // 实际中剩余字节数量
                for (int i = 0; i < otherLen; i++) {
                    BitSet otherBitSet = convert2BitSet(otherBytes[i]);
                    if (!otherBitSet.get(0) || otherBitSet.get(1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static BitSet convert2BitSet(int code) {
        BitSet bitSet = new BitSet(8);
        IntStream.range(0, 8).filter(i -> (0x1 & (code >> (8 - i - 1))) == 1).forEach(bitSet::set);
        return bitSet;
    }

    private static int getCountOfSequential(BitSet startBitSet) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (startBitSet.get(i)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
