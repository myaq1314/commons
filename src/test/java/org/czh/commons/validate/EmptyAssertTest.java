package org.czh.commons.validate;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("all")
public class EmptyAssertTest {

    @Test
    public void test() {
        Object obj = null;
        EmptyAssert.isNull(obj);
        EmptyAssert.allNull(obj);

        obj = 23;
        EmptyAssert.isNotNull(obj);
        EmptyAssert.allNotNull(obj);

        String text = null;
        EmptyAssert.isEmpty(text);
        EmptyAssert.allEmpty(text);
        text = "";
        EmptyAssert.isEmpty(text);
        EmptyAssert.allEmpty(text);

        text = "3";
        EmptyAssert.isNotEmpty(text);
        EmptyAssert.allNotEmpty(text);

        text = null;
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);
        text = "";
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);
        text = " ";
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);
        text = "\n";
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);
        text = "\t";
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);
        text = "\r";
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);
        text = " \n\t\r";
        EmptyAssert.isBlank(text);
        EmptyAssert.allBlank(text);

        text = "3";
        EmptyAssert.isNotBlank(text);
        EmptyAssert.allNotBlank(text);

        Collection<String> list = null;
        Collection<String> set = null;
        EmptyAssert.isEmpty(list);
        EmptyAssert.allEmpty(list);
        EmptyAssert.isEmpty(set);
        EmptyAssert.allEmpty(set);
        list = new ArrayList<>();
        set = new HashSet<>();
        EmptyAssert.isEmpty(list);
        EmptyAssert.allEmpty(list);
        EmptyAssert.isEmpty(set);
        EmptyAssert.allEmpty(set);

        list.add("123");
        set.add("123");
        EmptyAssert.isNotEmpty(list);
        EmptyAssert.allNotEmpty(list);
        EmptyAssert.isNotEmpty(set);
        EmptyAssert.allNotEmpty(set);

        Map<String, Integer> map = null;
        EmptyAssert.isEmpty(map);
        EmptyAssert.allEmpty(map);
        map = new HashMap<>();
        EmptyAssert.isEmpty(map);
        EmptyAssert.allEmpty(map);

        map.put("123", 124);
        EmptyAssert.isNotEmpty(map);
        EmptyAssert.allNotEmpty(map);

        byte[] bs = null;
        EmptyAssert.isEmpty(bs);
        EmptyAssert.allEmpty(bs);
        bs = new byte[0];
        EmptyAssert.isEmpty(bs);
        EmptyAssert.allEmpty(bs);

        bs = new byte[1];
        EmptyAssert.isNotEmpty(bs);
        EmptyAssert.allNotEmpty(bs);
        bs[0] = 1;
        EmptyAssert.isNotEmpty(bs);
        EmptyAssert.allNotEmpty(bs);

        short[] ss = null;
        EmptyAssert.isEmpty(ss);
        EmptyAssert.allEmpty(ss);
        ss = new short[0];
        EmptyAssert.isEmpty(ss);
        EmptyAssert.allEmpty(ss);

        ss = new short[1];
        EmptyAssert.isNotEmpty(ss);
        EmptyAssert.allNotEmpty(ss);
        ss[0] = 1;
        EmptyAssert.isNotEmpty(ss);
        EmptyAssert.allNotEmpty(ss);

        int[] is = null;
        EmptyAssert.isEmpty(is);
        EmptyAssert.allEmpty(is);
        is = new int[0];
        EmptyAssert.isEmpty(is);
        EmptyAssert.allEmpty(is);

        is = new int[1];
        EmptyAssert.isNotEmpty(is);
        EmptyAssert.allNotEmpty(is);
        is[0] = 1;
        EmptyAssert.isNotEmpty(is);
        EmptyAssert.allNotEmpty(is);

        long[] ls = null;
        EmptyAssert.isEmpty(ls);
        EmptyAssert.allEmpty(ls);
        ls = new long[0];
        EmptyAssert.isEmpty(ls);
        EmptyAssert.allEmpty(ls);

        ls = new long[1];
        EmptyAssert.isNotEmpty(ls);
        EmptyAssert.allNotEmpty(ls);
        ls[0] = 1L;
        EmptyAssert.isNotEmpty(ls);
        EmptyAssert.allNotEmpty(ls);

        float[] fs = null;
        EmptyAssert.isEmpty(fs);
        EmptyAssert.allEmpty(fs);
        fs = new float[0];
        EmptyAssert.isEmpty(fs);
        EmptyAssert.allEmpty(fs);

        fs = new float[1];
        EmptyAssert.isNotEmpty(fs);
        EmptyAssert.allNotEmpty(fs);
        fs[0] = 1F;
        EmptyAssert.isNotEmpty(fs);
        EmptyAssert.allNotEmpty(fs);

        double[] ds = null;
        EmptyAssert.isEmpty(ds);
        EmptyAssert.allEmpty(ds);
        ds = new double[0];
        EmptyAssert.isEmpty(ds);
        EmptyAssert.allEmpty(ds);

        ds = new double[1];
        EmptyAssert.isNotEmpty(ds);
        EmptyAssert.allNotEmpty(ds);
        ds[0] = 1;
        EmptyAssert.isNotEmpty(ds);
        EmptyAssert.allNotEmpty(ds);

        char[] cs = null;
        EmptyAssert.isEmpty(cs);
        EmptyAssert.allEmpty(cs);
        cs = new char[0];
        EmptyAssert.isEmpty(cs);
        EmptyAssert.allEmpty(cs);

        cs = new char[1];
        EmptyAssert.isNotEmpty(cs);
        EmptyAssert.allNotEmpty(cs);
        cs[0] = '1';
        EmptyAssert.isNotEmpty(cs);
        EmptyAssert.allNotEmpty(cs);

        boolean[] bos = null;
        EmptyAssert.isEmpty(bos);
        EmptyAssert.allEmpty(bos);
        bos = new boolean[0];
        EmptyAssert.isEmpty(bos);
        EmptyAssert.allEmpty(bos);

        bos = new boolean[1];
        EmptyAssert.isNotEmpty(bos);
        EmptyAssert.allNotEmpty(bos);
        bos[0] = true;
        EmptyAssert.isNotEmpty(bos);
        EmptyAssert.allNotEmpty(bos);

        BigDecimal[] bds = null;
        EmptyAssert.isEmpty(bds);
        EmptyAssert.allEmpty(bds);
        bds = new BigDecimal[0];
        EmptyAssert.isEmpty(bds);
        EmptyAssert.allEmpty(bds);

        bds = new BigDecimal[1];
        EmptyAssert.isNotEmpty(bds);
        EmptyAssert.allNotEmpty(bds);
        bds[0] = new BigDecimal(1);
        EmptyAssert.isNotEmpty(bds);
        EmptyAssert.allNotEmpty(bds);
    }
}
