package com.zhiyu.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class PasswordUtils {

    private static final int SALT_LENGTH = 16;

    /**
     * 对密码进行加盐哈希处理
     *
     * @param password 原始密码
     * @return 包含盐值和哈希密码的字符串，格式为 salt:hashedPassword
     */
    public static String hashPassword(String password) {
        // 生成盐值
        String salt = generateSalt();
        // 对密码进行哈希处理
        String hashedPassword = hashPasswordWithSalt(password, salt);
        // 返回包含盐值和哈希密码的字符串
        return salt + ":" + hashedPassword;
    }

    /**
     * 校验密码是否匹配
     *
     * @param password               原始密码
     * @param hashedPasswordWithSalt 包含盐值和哈希密码的字符串
     * @return 如果密码匹配返回 true，否则返回 false
     */
    public static boolean verifyPassword(String password, String hashedPasswordWithSalt) {
        // 从包含盐值和哈希密码的字符串中解析出盐值和哈希密码
        String[] parts = hashedPasswordWithSalt.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid hashedPasswordWithSalt format");
        }
        String salt = parts[0];
        String hashedPassword = parts[1];
        // 使用盐值对原始密码进行哈希处理
        String hashedPasswordAttempt = hashPasswordWithSalt(password, salt);
        // 比较哈希密码是否匹配
        return hashedPassword.equals(hashedPasswordAttempt);
    }

    /**
     * 使用盐值对密码进行哈希处理
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 哈希后的密码
     */
    private static String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    /**
     * 生成随机盐值
     *
     * @return 随机盐值的 Base64 编码字符串
     */
    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static void main(String[] args) {
        log.info(hashPassword("123456"));
        boolean isVerify = verifyPassword("123456",
                "B0S1q7EetT6Y22qVo7Ogng==:yMDAUQDAWiWUWaA0zGcakv/TEV0VrDUsisanRvmigX0=");
        log.info(String.valueOf(isVerify));


    }
}
