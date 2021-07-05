package org.czh.commons.validate;

import org.czh.commons.entity.example.ParentTest;
import org.czh.commons.entity.example.SonTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-05-02
 * email 916419307@qq.com
 */
public class EqualsValidateTest {

    @Test
    public void equalsTest() {
        Assert.assertTrue(EqualsValidate.equals(new SonTest(), new SonTest()));
        Assert.assertFalse(EqualsValidate.equals(new SonTest[]{new SonTest(), new SonTest()}, new SonTest[]{new SonTest()}));

        Assert.assertTrue(EqualsValidate.equals(new byte[]{1, 2, 3}, new byte[]{1, 2, 3}));
        Assert.assertTrue(EqualsValidate.equals(new short[]{1, 2, 3}, new short[]{1, 2, 3}));
        Assert.assertTrue(EqualsValidate.equals(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        Assert.assertTrue(EqualsValidate.equals(new long[]{1L, 2L, 3L}, new long[]{1L, 2L, 3L}));
        Assert.assertTrue(EqualsValidate.equals(new float[]{1F, 2F, 3F}, new float[]{1f, 2f, 3f}));
        Assert.assertTrue(EqualsValidate.equals(new double[]{1D, 2D, 3D}, new double[]{1d, 2d, 3d}));
        Assert.assertTrue(EqualsValidate.equals(new char[]{1, 2, 3}, new char[]{1, 2, 3}));
        Assert.assertTrue(EqualsValidate.equals(new boolean[]{true, true, false}, new boolean[]{true, true, false}));
    }

    @Test
    public void notEqualsTest() {
        Assert.assertTrue(EqualsValidate.notEquals(new SonTest(), new ParentTest()));
        Assert.assertTrue(EqualsValidate.notEquals(new SonTest[]{new SonTest(), new SonTest()}, new SonTest[]{new SonTest()}));

        Assert.assertTrue(EqualsValidate.notEquals(new byte[]{1, 2, 3}, new byte[]{1, 2, 4}));
        Assert.assertTrue(EqualsValidate.notEquals(new short[]{1, 2, 3}, new short[]{1, 2, 4}));
        Assert.assertTrue(EqualsValidate.notEquals(new int[]{1, 2, 3}, new int[]{1, 2, 4}));
        Assert.assertTrue(EqualsValidate.notEquals(new long[]{1L, 2L, 3L}, new long[]{1L, 2L, 4L}));
        Assert.assertTrue(EqualsValidate.notEquals(new float[]{1F, 2F, 3F}, new float[]{1f, 2f, 4f}));
        Assert.assertTrue(EqualsValidate.notEquals(new double[]{1D, 2D, 3D}, new double[]{1d, 2d, 4d}));
        Assert.assertTrue(EqualsValidate.notEquals(new char[]{1, 2, 3}, new char[]{1, 2, 4}));
        Assert.assertTrue(EqualsValidate.notEquals(new boolean[]{true, true, false}, new boolean[]{true, true}));
    }
}
