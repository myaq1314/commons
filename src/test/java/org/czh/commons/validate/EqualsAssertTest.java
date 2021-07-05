package org.czh.commons.validate;

import org.czh.commons.entity.example.ParentTest;
import org.czh.commons.entity.example.SonTest;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-05-07
 * email 916419307@qq.com
 */
public class EqualsAssertTest {

    @Test
    public void equalsTest() {
        EqualsAssert.isEquals(new SonTest(), new SonTest());
        EqualsAssert.isEquals(new SonTest[]{new SonTest()}, new SonTest[]{new SonTest()});

        EqualsAssert.isEquals(new byte[]{1, 2, 3}, new byte[]{1, 2, 3});
        EqualsAssert.isEquals(new short[]{1, 2, 3}, new short[]{1, 2, 3});
        EqualsAssert.isEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        EqualsAssert.isEquals(new long[]{1L, 2L, 3L}, new long[]{1L, 2L, 3L});
        EqualsAssert.isEquals(new float[]{1F, 2F, 3F}, new float[]{1f, 2f, 3f});
        EqualsAssert.isEquals(new double[]{1D, 2D, 3D}, new double[]{1d, 2d, 3d});
        EqualsAssert.isEquals(new char[]{1, 2, 3}, new char[]{1, 2, 3});
        EqualsAssert.isEquals(new boolean[]{true, true, false}, new boolean[]{true, true, false});

        EqualsAssert.allEquals(new SonTest(), new SonTest());
        EqualsAssert.allEquals(new SonTest[]{new SonTest()}, new SonTest[]{new SonTest()});

        EqualsAssert.allEquals(new byte[]{1, 2, 3}, new byte[]{1, 2, 3});
        EqualsAssert.allEquals(new short[]{1, 2, 3}, new short[]{1, 2, 3});
        EqualsAssert.allEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        EqualsAssert.allEquals(new long[]{1L, 2L, 3L}, new long[]{1L, 2L, 3L});
        EqualsAssert.allEquals(new float[]{1F, 2F, 3F}, new float[]{1f, 2f, 3f});
        EqualsAssert.allEquals(new double[]{1D, 2D, 3D}, new double[]{1d, 2d, 3d});
        EqualsAssert.allEquals(new char[]{1, 2, 3}, new char[]{1, 2, 3});
        EqualsAssert.allEquals(new boolean[]{true, true, false}, new boolean[]{true, true, false});
    }

    @Test
    public void notEqualsTest() {
        EqualsAssert.isNotEquals(new SonTest(), new ParentTest());
        EqualsAssert.isNotEquals(new SonTest[]{new SonTest(), new SonTest()}, new SonTest[]{new SonTest()});

        EqualsAssert.isNotEquals(new byte[]{1, 2, 3}, new byte[]{1, 2, 4});
        EqualsAssert.isNotEquals(new short[]{1, 2, 3}, new short[]{1, 2, 4});
        EqualsAssert.isNotEquals(new int[]{1, 2, 3}, new int[]{1, 2, 4});
        EqualsAssert.isNotEquals(new long[]{1L, 2L, 3L}, new long[]{1L, 2L, 4L});
        EqualsAssert.isNotEquals(new float[]{1F, 2F, 3F}, new float[]{1f, 2f, 4f});
        EqualsAssert.isNotEquals(new double[]{1D, 2D, 3D}, new double[]{1d, 2d, 4d});
        EqualsAssert.isNotEquals(new char[]{1, 2, 3}, new char[]{1, 2, 4});
        EqualsAssert.isNotEquals(new boolean[]{true, true, false}, new boolean[]{true, true});

        EqualsAssert.allNotEquals(new SonTest(), new ParentTest());
        EqualsAssert.allNotEquals(new SonTest[]{new SonTest(), new SonTest()}, new SonTest[]{new SonTest()});

        EqualsAssert.allNotEquals(new byte[]{1, 2, 3}, new byte[]{1, 2, 4});
        EqualsAssert.allNotEquals(new short[]{1, 2, 3}, new short[]{1, 2, 4});
        EqualsAssert.allNotEquals(new int[]{1, 2, 3}, new int[]{1, 2, 4});
        EqualsAssert.allNotEquals(new long[]{1L, 2L, 3L}, new long[]{1L, 2L, 4L});
        EqualsAssert.allNotEquals(new float[]{1F, 2F, 3F}, new float[]{1f, 2f, 4f});
        EqualsAssert.allNotEquals(new double[]{1D, 2D, 3D}, new double[]{1d, 2d, 4d});
        EqualsAssert.allNotEquals(new char[]{1, 2, 3}, new char[]{1, 2, 4});
        EqualsAssert.allNotEquals(new boolean[]{true, true, false}, new boolean[]{true, true});
    }
}
