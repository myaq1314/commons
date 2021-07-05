package org.czh.commons.utils.encrypt.irreversible;

import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class SHAUtilTest {

    @Test
    public void test() {
        String salt = SHAUtil.getSalt(32);
        System.out.println(salt); // 844f77f9196859d501819313881b5e2c

        String src = "123456";
        System.out.println(src); // 123456

        String dst1 = SHAUtil.encode(EncryptConstant.SHAType.SHA_1, src, salt);
        String dst12 = SHAUtil.encode(EncryptConstant.SHAType.SHA_1, src, salt);
        System.out.println(dst1); // b1733f92e9372c4c33b7544e210ea6004a93033e
        FlagAssert.isTrue(SHAUtil.verify(EncryptConstant.SHAType.SHA_1, src, dst1, salt));
        EqualsAssert.isEquals(dst1, dst12);

        String dst224 = SHAUtil.encode(EncryptConstant.SHAType.SHA_224, src, salt);
        String dst2242 = SHAUtil.encode(EncryptConstant.SHAType.SHA_224, src, salt);
        System.out.println(dst224); // 2b6db796983488d24bd5da59ff1a89ab822e75b9cb11f8a9fb55f14f
        FlagAssert.isTrue(SHAUtil.verify(EncryptConstant.SHAType.SHA_224, src, dst224, salt));
        EqualsAssert.isEquals(dst224, dst2242);

        String dst256 = SHAUtil.encode(EncryptConstant.SHAType.SHA_256, src, salt);
        String dst2562 = SHAUtil.encode(EncryptConstant.SHAType.SHA_256, src, salt);
        System.out.println(dst256); // 1184305bdf1e454a12d6cacbfe200bbf0e6ff47271a466bc5f0dfc057ed977be
        FlagAssert.isTrue(SHAUtil.verify(EncryptConstant.SHAType.SHA_256, src, dst256, salt));
        EqualsAssert.isEquals(dst256, dst2562);

        String dst384 = SHAUtil.encode(EncryptConstant.SHAType.SHA_384, src, salt);
        String dst3842 = SHAUtil.encode(EncryptConstant.SHAType.SHA_384, src, salt);
        System.out.println(dst384); // d12c737ea9c2b476b8181162546e06fa8bb268a67fda74761aeafe452674bc8d12c61c6386cc6ce9dd8fdecb2ef16a49
        FlagAssert.isTrue(SHAUtil.verify(EncryptConstant.SHAType.SHA_384, src, dst384, salt));
        EqualsAssert.isEquals(dst384, dst3842);

        String dst512 = SHAUtil.encode(EncryptConstant.SHAType.SHA_512, src, salt);
        String dst5122 = SHAUtil.encode(EncryptConstant.SHAType.SHA_512, src, salt);
        System.out.println(dst512); // 38e3f9a4c26e74e954564f6cdf2d39efb137c78310cfd199f7bed37936b0c204993cc3b8ae239ce7a78d11448242708f327fef12c7230d88c94f75e7549a7325
        FlagAssert.isTrue(SHAUtil.verify(EncryptConstant.SHAType.SHA_512, src, dst512, salt));
        EqualsAssert.isEquals(dst512, dst5122);
    }
}
