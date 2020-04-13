package com.pigs.springbootpigscrm.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * shiro 密码安全工具类
 *
 * @author PIGS
 */
public class SecurityUtils {

    /**
     * 使用原生密码+盐 通过md5加密生成密码  的方法
     *
     * @param password        原生密码
     * @param credentialsSalt 加强盐  （userName +salt）
     * @return
     */
    public static String encryptPassword(String password, String credentialsSalt) {
        /**
         * 参数：加密名称，原生密码，盐，加密次数
         */
        return new SimpleHash("md5", password, credentialsSalt, 2).toHex();
    }

    /**
     * 生成随机盐  (插入记录的时候，可以调用)
     */
    public static String randomSalt() {
        /**
         * 一个Byte占两个字节，此处生成的24字节，字符串长度为48
         */
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(24).toHex();
        return hex;
    }

}
