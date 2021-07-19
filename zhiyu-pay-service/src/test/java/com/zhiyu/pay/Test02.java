package com.zhiyu.pay;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSON;
import com.zhiyu.pay.entity.dto.PayDTO;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/11/18
 */
public class Test02 {

    public static void main(String[] args) {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJo9Sl/nAqb7pEyBFnsRCJVTnV8ReEoqFNC1UzE7YWIJUKluGwtQXFNGZlxa+LzkzeDJTgpx9bwZlajejjs3c3ODzxlNcAFh/svSQlBnwyZeqUNvMXehT4SmtgVQvtEzRx0PeYQAIPgbs+mHBsYnp75tVzGip/eoL+1bsBrrjY+ZAgMBAAECgYABgnddJDw+KQ94KtMVWses+hYu+6zwx7DC9zD5T4jtOV6dyrfyJScpYhj3FYvcNQtMnf1imq/XSrlqH12G6yZfd4X8uQZvK8U6Bc21dTyaePvjOSkvMYT7Fx6wm99fcntOdvL4w3CsCEZL3rcUpznWCAlgntzBswSWfQxwKLuSZQJBANFnZya8altxerFa7XpAbtJzIM4YxYASE26GWTlu0hrW+RLvtR2d3dgUB6bUrlRKll2AFsrEdoR5WgZqc7wRPIUCQQC8j3ejiTAEYL2cVOTLmlb4uLduyZLHg3rMKK00HVLIt5mjPv4c8g/tzrfn/2unMDIHvC7XlBmGQUx1h0LnhC0FAkBt/qvfXA9eE0DIkqO1mMzdvN3ZhIeR22zbnLSXh4/Ty2IwQTvY38jdLMBMMOGVo0xSturB/erncTsJrbc3bbJlAkBaIrquEgZCEXySHoLAUScB/kqxSuo/NTqeHVOuddX83Mc0oggXDh9PLmH7JL3xTbicufgqVUFeTBiglegFg8mBAkEAzEb4GRosVmhSIHfOsIjmyZU1ffhz4sqTMs/FOBAvWsbaNectIKisCEp1KZ4AwVo41/kdibCH5M1Yuuh3PZf4Vg==";
        String publiceKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaPUpf5wKm+6RMgRZ7EQiVU51fEXhKKhTQtVMxO2FiCVCpbhsLUFxTRmZcWvi85M3gyU4KcfW8GZWo3o47N3Nzg88ZTXABYf7L0kJQZ8MmXqlDbzF3oU+EprYFUL7RM0cdD3mEACD4G7PphwbGJ6e+bVcxoqf3qC/tW7Aa642PmQIDAQAB";
        RSA rsa = new RSA(privateKey, publiceKey);

////获得私钥
//        rsa.getPrivateKey();
//        rsa.getPrivateKeyBase64();
////获得公钥
//        rsa.getPublicKey();
//        rsa.getPublicKeyBase64();


        PayDTO payDTO = new PayDTO();
        payDTO.setSubject("物品");

        //公钥加密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes(JSON.toJSONString(payDTO), CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        //私钥解密
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        System.out.println(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));


//         //私钥加密
//        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes(JSON.toJSONString(encrypt), CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
//        //公钥解密
//        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
//        System.out.println(StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

//        Map<String, String> map=getRsaKey();
//        for (String key : map.keySet()) {
//            System.out.println(key);
//            System.out.println(map.get(key));
//            System.out.println("=========");
//        }
    }


    public static Map<String, String> getRsaKey() {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        String privateKeyStr = Base64.encode(pair.getPrivate().getEncoded());
        String publicKeyStr = Base64.encode(pair.getPublic().getEncoded());
        HashMap<String, String> keyMap = new HashMap<>(2);
        keyMap.put("privateKey", privateKeyStr);
        keyMap.put("publicKey", publicKeyStr);
        return keyMap;
    }

}
