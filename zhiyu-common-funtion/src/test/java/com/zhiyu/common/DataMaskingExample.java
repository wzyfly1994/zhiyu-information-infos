package com.zhiyu.common;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.StringEscapeUtils;

/**
 * @author: wengzhiyu
 * @create: 2023-03-18 04:08
 **/
public class DataMaskingExample {
    // 邮箱脱敏
    public static String maskEmail(String email) {
        int atIdx = email.indexOf("@");
        if (atIdx < 0) {
            return email;
        }
        String prefix = email.substring(0, atIdx);
        int len = prefix.length();
        if (len <= 2) {
            return email;
        }
        String firstChar = prefix.substring(0, 1);
        String lastChar = prefix.substring(len - 1, len);
        String mask = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build().generate(2);
        return firstChar + mask + lastChar + email.substring(atIdx);
    }

    // 手机号脱敏
    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.length() != 11) {
            return mobile;
        }
        return mobile.substring(0, 3) + "****" + mobile.substring(7);
    }

    // 身份证号脱敏
    public static String maskIdCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return idCard;
        }
        return idCard.substring(0, 4) + "**********" + idCard.substring(14);
    }

    // 地址脱敏
    public static String maskAddress(String address) {
        if (address == null) {
            return address;
        }
        String[] parts = address.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(maskString(parts[i]));
        }
        return sb.toString();
    }

    // 字符串脱敏
    public static String maskString(String str) {
        if (str == null) {
            return str;
        }
        int len = str.length();
        if (len <= 2) {
            return str;
        }
        String firstChar = str.substring(0, 1);
        String lastChar = str.substring(len - 1, len);
        String mask = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build().generate(2);
        return firstChar + mask + lastChar;
    }

    public static void main(String[] args) {
        String email = "user@example.com";
        String mobile = "13812345678";
        String idCard = "310101198001010101";
        String address = "上海市浦东新区XX路XX号";
        String password = "passw0rd";

        System.out.println("Email: " + maskEmail(email));
        System.out.println("Mobile: " + maskMobile(mobile));
        System.out.println("ID card: " + maskIdCard(idCard));
        System.out.println("Address: " + maskAddress(address));
        System.out.println("Password: " + maskString(password));
    }
}
