package org.czh.commons.validate;

import org.junit.Assert;
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
public class EmptyValidateTest {

    @Test
    public void test() {
        Object obj = null;
        Assert.assertTrue(EmptyValidate.isNull(obj));

        obj = 23;
        Assert.assertTrue(EmptyValidate.isNotNull(obj));

        String text = null;
        Assert.assertTrue(EmptyValidate.isEmpty(text));
        text = "";
        Assert.assertTrue(EmptyValidate.isEmpty(text));

        text = "3";
        Assert.assertTrue(EmptyValidate.isNotEmpty(text));

        text = null;
        Assert.assertTrue(EmptyValidate.isBlank(text));
        text = "";
        Assert.assertTrue(EmptyValidate.isBlank(text));
        text = " ";
        Assert.assertTrue(EmptyValidate.isBlank(text));
        text = "\n";
        Assert.assertTrue(EmptyValidate.isBlank(text));
        text = "\t";
        Assert.assertTrue(EmptyValidate.isBlank(text));
        text = "\r";
        Assert.assertTrue(EmptyValidate.isBlank(text));
        text = " \n\t\r";
        Assert.assertTrue(EmptyValidate.isBlank(text));

        text = "3";
        Assert.assertTrue(EmptyValidate.isNotBlank(text));

        Collection<String> list = null;
        Collection<String> set = null;
        Assert.assertTrue(EmptyValidate.isEmpty(list));
        Assert.assertTrue(EmptyValidate.isEmpty(set));
        list = new ArrayList<>();
        set = new HashSet<>();
        Assert.assertTrue(EmptyValidate.isEmpty(list));
        Assert.assertTrue(EmptyValidate.isEmpty(set));

        list.add("123");
        set.add("123");
        Assert.assertTrue(EmptyValidate.isNotEmpty(list));
        Assert.assertTrue(EmptyValidate.isNotEmpty(set));

        Map<String, Integer> map = null;
        Assert.assertTrue(EmptyValidate.isEmpty(map));
        map = new HashMap<>();
        Assert.assertTrue(EmptyValidate.isEmpty(map));

        map.put("123", 124);
        Assert.assertTrue(EmptyValidate.isNotEmpty(map));

        byte[] bs = null;
        Assert.assertTrue(EmptyValidate.isEmpty(bs));
        bs = new byte[0];
        Assert.assertTrue(EmptyValidate.isEmpty(bs));

        bs = new byte[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(bs));
        bs[0] = 1;
        Assert.assertTrue(EmptyValidate.isNotEmpty(bs));

        short[] ss = null;
        Assert.assertTrue(EmptyValidate.isEmpty(ss));
        ss = new short[0];
        Assert.assertTrue(EmptyValidate.isEmpty(ss));

        ss = new short[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(ss));
        ss[0] = 1;
        Assert.assertTrue(EmptyValidate.isNotEmpty(ss));

        int[] is = null;
        Assert.assertTrue(EmptyValidate.isEmpty(is));
        is = new int[0];
        Assert.assertTrue(EmptyValidate.isEmpty(is));

        is = new int[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(is));
        is[0] = 1;
        Assert.assertTrue(EmptyValidate.isNotEmpty(is));

        long[] ls = null;
        Assert.assertTrue(EmptyValidate.isEmpty(ls));
        ls = new long[0];
        Assert.assertTrue(EmptyValidate.isEmpty(ls));

        ls = new long[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(ls));
        ls[0] = 1L;
        Assert.assertTrue(EmptyValidate.isNotEmpty(ls));

        float[] fs = null;
        Assert.assertTrue(EmptyValidate.isEmpty(fs));
        fs = new float[0];
        Assert.assertTrue(EmptyValidate.isEmpty(fs));

        fs = new float[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(fs));
        fs[0] = 1F;
        Assert.assertTrue(EmptyValidate.isNotEmpty(fs));

        double[] ds = null;
        Assert.assertTrue(EmptyValidate.isEmpty(ds));
        ds = new double[0];
        Assert.assertTrue(EmptyValidate.isEmpty(ds));

        ds = new double[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(ds));
        ds[0] = 1;
        Assert.assertTrue(EmptyValidate.isNotEmpty(ds));

        char[] cs = null;
        Assert.assertTrue(EmptyValidate.isEmpty(cs));
        cs = new char[0];
        Assert.assertTrue(EmptyValidate.isEmpty(cs));

        cs = new char[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(cs));
        cs[0] = '1';
        Assert.assertTrue(EmptyValidate.isNotEmpty(cs));

        boolean[] bos = null;
        Assert.assertTrue(EmptyValidate.isEmpty(bos));
        bos = new boolean[0];
        Assert.assertTrue(EmptyValidate.isEmpty(bos));

        bos = new boolean[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(bos));
        bos[0] = true;
        Assert.assertTrue(EmptyValidate.isNotEmpty(bos));

        BigDecimal[] bds = null;
        Assert.assertTrue(EmptyValidate.isEmpty(bds));
        bds = new BigDecimal[0];
        Assert.assertTrue(EmptyValidate.isEmpty(bds));

        bds = new BigDecimal[1];
        Assert.assertTrue(EmptyValidate.isNotEmpty(bds));
        bds[0] = new BigDecimal(1);
        Assert.assertTrue(EmptyValidate.isNotEmpty(bds));
    }
}
