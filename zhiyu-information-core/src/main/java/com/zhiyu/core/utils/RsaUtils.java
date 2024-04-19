package com.zhiyu.core.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class RsaUtils {

    private static final String SRC = "123456";

    public static void main(String[] args) throws Exception {
//        System.out.println("\n");
//        RsaKeyPair keyPair = generateKeyPair();
//        System.out.println("公钥：" + keyPair.getPublicKey());
//        System.out.println("私钥：" + keyPair.getPrivateKey());
//        System.out.println("\n");
//        test1(keyPair);
//        System.out.println("\n");
//        test2(keyPair);
//        System.out.println("\n");


        String value = "123456";
        String privateKey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAN05ILK43h2MFkKr43AL6kkh8OGWsJXXX6MCFelA7pMttEXdnc41b0BetL3Gu0CMXp+HWcs+muPQIIuRyDQISf6xQcNsyBJg7VYZC3lgy19dKSB8USM+5bhjRPCWehrbyLxw2530XQdIbiB3nm0r5Bw0gCXl1s2oUXkPeUGSak5hAgMBAAECgYEAtxplugfRsFsWwbBL253BGS8ClXWISz2sZtt/D0UzMpaNs5Yu21LW9kxtBPbMXizQ1PHOnK8AvLazeVH8hzSlKKNhqTdiQa6F3MDICQRJOqS36K1ulULBjauouhG09l3gn69V5mJNdkpRdLw0e7rDV4xAKvUWz/yd8l1zM6ACYwECQQD5l2h+8CKVkAebpUwN/ulQOWe6eN1+sHnQsfE/xSMzMhnmw0AaePj/LzeGf1eHw63cdW4WMxa5hFBBZ5uj0ak5AkEA4udAcFQSOTwRAv4daHWqrBnP85dIZ/+N6N8fdP3CZClh69++j3nPPFp+okhVIVi7cTUGzuUaCxIuUfDfGOkWaQJBANWg8IHly9Qz6S8hdk1saAqQd+Y1WW/8YOdtxs4MUCqbsWvApveBn63bnizyhdrgc8mEgWti4wp64653ndHDvJECQQC2kPDFMK/VA2KAsOwCj6J6hO5QBapJm1KYS2apIEBXjQBv5AAUuyKmYf3AH0j34sg2pz7KG4Zf4AoK/i7TgT/5AkEAnf3Kcm8vLZA1V/XER3Ou2jMKot4A/+P+/DBLM8kHKnj97TCmOMIc67mCfdDGMB35JxA5+Tp1jxLhlSVmQ2X0PQ==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdOSCyuN4djBZCq+NwC+pJIfDhlrCV11+jAhXpQO6TLbRF3Z3ONW9AXrS9xrtAjF6fh1nLPprj0CCLkcg0CEn+sUHDbMgSYO1WGQt5YMtfXSkgfFEjPuW4Y0Twlnoa28i8cNud9F0HSG4gd55tK+QcNIAl5dbNqFF5D3lBkmpOYQIDAQAB";

        //String encryptPublic = encryptByPublicKey(publicKey, value);

        // 前端公钥加密的字符串
        String encryptStrByPublic = "APDLuEM0vS90KjwHXbFFUqaIbP+aOaSvbJcipJwqQtj/dArqLAs2APwWICDG5QQzaLPbSKRYsQdLykWGBmt6ZpkT1fc04uzM4Ur0PmiTmVX4uEno+kUdbBCF+8bvKyzc9ZY7Z/Tgopa97t1Fgmbx/DOz/CZu1dmF1zaQFGMtagE=";

        // 私钥解密
        String pwd = decryptByPrivateKey(privateKey, encryptStrByPublic);

        System.out.printf(pwd);

    }

    /**
     * 公钥加密私钥解密
     */
    private static void test1(RsaKeyPair keyPair) throws Exception {
        System.out.println("***************** 公钥加密私钥解密开始 *****************");
        String text1 = encryptByPublicKey(keyPair.getPublicKey(), RsaUtils.SRC);
        String text2 = decryptByPrivateKey(keyPair.getPrivateKey(), text1);
        System.out.println("加密前：" + RsaUtils.SRC);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (RsaUtils.SRC.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 公钥加密私钥解密结束 *****************");
    }

    /**
     * 私钥加密公钥解密
     *
     * @throws Exception /
     */
    private static void test2(RsaKeyPair keyPair) throws Exception {
        System.out.println("***************** 私钥加密公钥解密开始 *****************");
        String text1 = encryptByPrivateKey(keyPair.getPrivateKey(), RsaUtils.SRC);
        String text2 = decryptByPublicKey(keyPair.getPublicKey(), text1);
        System.out.println("加密前：" + RsaUtils.SRC);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (RsaUtils.SRC.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 私钥加密公钥解密结束 *****************");
    }

    /**
     * 公钥解密
     *
     * @param publicKeyText 公钥
     * @param text          待解密的信息
     * @return /
     * @throws Exception /
     */
    public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = doLongerCipherFinal(Cipher.DECRYPT_MODE, cipher, Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyText 私钥
     * @param text           待加密的信息
     * @return /
     * @throws Exception /
     */
    public static String encryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = doLongerCipherFinal(Cipher.ENCRYPT_MODE, cipher, text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyText 私钥
     * @param text           待解密的文本
     * @return /
     * @throws Exception /
     */
    public static String decryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = doLongerCipherFinal(Cipher.DECRYPT_MODE, cipher, Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText 公钥
     * @param text          待加密的文本
     * @return /
     */
    public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = doLongerCipherFinal(Cipher.ENCRYPT_MODE, cipher, text.getBytes());
        return Base64.encodeBase64String(result);
    }

    private static byte[] doLongerCipherFinal(int opMode, Cipher cipher, byte[] source) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (opMode == Cipher.DECRYPT_MODE) {
            out.write(cipher.doFinal(source));
        } else {
            int offset = 0;
            int totalSize = source.length;
            while (totalSize - offset > 0) {
                int size = Math.min(cipher.getOutputSize(0) - 11, totalSize - offset);
                out.write(cipher.doFinal(source, offset, size));
                offset += size;
            }
        }
        out.close();
        return out.toByteArray();
    }

    /**
     * 构建RSA密钥对
     *
     * @return /
     * @throws NoSuchAlgorithmException /
     */
    public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }


    /**
     * RSA密钥对对象
     */
    public static class RsaKeyPair {

        private final String publicKey;
        private final String privateKey;

        public RsaKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

    }
}
