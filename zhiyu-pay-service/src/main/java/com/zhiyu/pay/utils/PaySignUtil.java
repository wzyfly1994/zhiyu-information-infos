package com.zhiyu.pay.utils;

import com.zhiyu.pay.common.exception.BusinessException;
import com.zhiyu.pay.config.PayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wengzhiyu
 * @date 2020/11/19
 */
@Slf4j
public class PaySignUtil {
    private static final String MD5 = "MD5";
    private static final String RSA = "RSA";
    private static final String SIGN = "sign";

    /**
     * 加签
     *
     * @param payConfig
     * @param map
     * @return
     */
    public static void addSign(PayConfig payConfig, TreeMap<String, String> map) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BusinessException("加签失败：实体转Map为空");
        }
        map.remove(SIGN);
        String params = splice(map);
        if (MD5.equals(payConfig.getSignType())) {
            map.put(SIGN, md5(params.getBytes(StandardCharsets.UTF_8)));
        } else {
            map.put(SIGN, rsaSign(params, payConfig.getPrivateKey()));
        }
    }

    /**
     * 验签
     *
     * @param payConfig
     * @param map
     * @return
     */
    public static boolean verifySign(PayConfig payConfig, TreeMap<String, String> map) {
        if (CollectionUtils.isEmpty(map) || !map.containsKey(SIGN)) {
            return false;
        }
        String sign = map.remove(SIGN);
        String params = splice(map);
        if (MD5.equals(payConfig.getSignType())) {
            return sign.toLowerCase().equals(md5(params.getBytes(StandardCharsets.UTF_8)).toLowerCase());
        } else {
            return rsaVerify(params, sign, payConfig.getPublicKey());
        }
    }

    /**
     * 拼接参数
     *
     * @param map
     * @return
     */
    private static String splice(TreeMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * md5
     *
     * @param b
     * @return
     */
    public static String md5(byte[] b) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuilder outStrBuf = new StringBuilder(32);
            for (byte value : hash) {
                int v = value & 0xFF;
                if (v < 16) {
                    outStrBuf.append('0');
                }
                outStrBuf.append(Integer.toString(v, 16).toLowerCase());
            }
            return outStrBuf.toString();
        } catch (Exception e) {
            log.error("MD5加签失败：" + e);
            throw new BusinessException("MD5加签失败！");
        }
    }

    /**
     * RSA加签
     *
     * @param content
     * @param privateKey
     * @return
     */
    private static String rsaSign(String content, String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes())));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            log.error("RSA加签失败：" + e);
            throw new BusinessException("RSA加签失败！");
        }
    }

    /**
     * RSA验签
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean rsaVerify(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey.getBytes())));
            java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            log.error("RSA验签失败：" + "content = " + content + ",sign=" + sign + e);
            return false;
        }
    }
}
