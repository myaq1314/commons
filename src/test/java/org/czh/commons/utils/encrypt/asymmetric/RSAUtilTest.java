package org.czh.commons.utils.encrypt.asymmetric;

import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.utils.encrypt.symmetric.Base64Util;
import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class RSAUtilTest {

    private String srcString;
    private byte[] srcBytes;
    private Map<String, String> keyStringMap;
    private PrivateKey privateKeyBean;
    private PublicKey publicKeyBean;
    private String publicKeyDstString;
    private byte[] publicKeySrcBytes;
    private String privateKeyDstString;
    private byte[] privateKeySrcBytes;

    @Before
    public void before() {
        srcString = "123456";
        srcBytes = srcString.getBytes();
        this.keyStringMap = RSAUtil.createKeyStringMap(512);
        this.privateKeyBean = RSAUtil.getPrivateKey(keyStringMap.get(EncryptConstant.getRSAPrivateKey()));
        this.publicKeyBean = RSAUtil.getPublicKey(keyStringMap.get(EncryptConstant.getRSAPublicKey()));
        this.publicKeyDstString = RSAUtil.encodeToString(this.srcString, this.publicKeyBean);
        this.publicKeySrcBytes = Base64Util.decode(this.publicKeyDstString);
        this.privateKeyDstString = RSAUtil.encodeToString(this.srcString, this.privateKeyBean);
        this.privateKeySrcBytes = Base64Util.decode(this.privateKeyDstString);
    }

    @Test
    public void testGetKey() {
        String privateKeyString = this.keyStringMap.get(EncryptConstant.getRSAPrivateKey());
        String privateKeyString2 = Base64Util.encodeToString(this.privateKeyBean.getEncoded());
        EqualsAssert.isEquals(privateKeyString, privateKeyString2);

        String publicKeyString = this.keyStringMap.get(EncryptConstant.getRSAPublicKey());
        String publicKeyString2 = Base64Util.encodeToString(this.publicKeyBean.getEncoded());
        EqualsAssert.isEquals(publicKeyString, publicKeyString2);
    }

    @Test
    public void testEncodeAndDecode() {
        String dst1 = RSAUtil.encodeToString(this.srcString, this.privateKeyBean);
        String src1 = RSAUtil.decodeToString(dst1, this.publicKeyBean);
        EqualsAssert.isEquals(this.srcString, src1);

        String dst2 = RSAUtil.encodeToString(this.srcString, this.publicKeyBean);
        String src2 = RSAUtil.decodeToString(dst2, this.privateKeyBean);
        EqualsAssert.isEquals(this.srcString, src2);

        String dst3 = RSAUtil.encodeToString(this.srcBytes, this.privateKeyBean);
        byte[] srcBytes3 = RSAUtil.decode(dst3, this.publicKeyBean);
        EqualsAssert.isEquals(this.srcBytes, srcBytes3);

        String dst4 = RSAUtil.encodeToString(this.srcBytes, this.publicKeyBean);
        byte[] srcBytes4 = RSAUtil.decode(dst4, this.privateKeyBean);
        EqualsAssert.isEquals(this.srcBytes, srcBytes4);

        byte[] dstBytes5 = RSAUtil.encode(this.srcString, this.privateKeyBean);
        String src5 = RSAUtil.decodeToString(dstBytes5, this.publicKeyBean);
        EqualsAssert.isEquals(this.srcString, src5);

        byte[] dstBytes6 = RSAUtil.encode(this.srcString, this.publicKeyBean);
        String src6 = RSAUtil.decodeToString(dstBytes6, this.privateKeyBean);
        EqualsAssert.isEquals(this.srcString, src6);

        byte[] dstBytes7 = RSAUtil.encode(this.srcBytes, this.privateKeyBean);
        byte[] srcBytes7 = RSAUtil.decode(dstBytes7, this.publicKeyBean);
        EqualsAssert.isEquals(this.srcBytes, srcBytes7);

        byte[] dstBytes8 = RSAUtil.encode(this.srcBytes, this.publicKeyBean);
        byte[] srcBytes8 = RSAUtil.decode(dstBytes8, this.privateKeyBean);
        EqualsAssert.isEquals(this.srcBytes, srcBytes8);
    }

    @Test
    public void testVerify() {
        FlagAssert.isTrue(RSAUtil.verify(this.srcString, this.privateKeyDstString, this.publicKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcString, this.publicKeyDstString, this.privateKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcString, this.privateKeySrcBytes, this.publicKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcString, this.publicKeySrcBytes, this.privateKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcBytes, this.privateKeyDstString, this.publicKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcBytes, this.publicKeyDstString, this.privateKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcBytes, this.privateKeySrcBytes, this.publicKeyBean));
        FlagAssert.isTrue(RSAUtil.verify(this.srcBytes, this.publicKeySrcBytes, this.privateKeyBean));
    }
}
