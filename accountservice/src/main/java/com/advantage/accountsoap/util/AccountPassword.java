package com.advantage.accountsoap.util;

import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Error;

import java.security.NoSuchAlgorithmException;

public class AccountPassword {
    private String salt;
    private String password;
    private static final Logger logger = Logger.getLogger(AccountPassword.class);

    public AccountPassword(String userName, String password) {
        this.salt = setSalt(userName);
        this.password = password;
    }

    public String getEncryptedPassword() {
        return SHA1(this.salt + SHA1(this.password));
    }

    private String setSalt(String salt) {
        return new StringBuilder(salt).reverse().toString();
    }

    public static String SHA1(String value) {
        java.security.MessageDigest d = null;
        try {
            d = java.security.MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            logger.fatal("Contact with support team: SHA-1 is wrong algoritm", e);
            throw new RuntimeException("SHA-1 is wrong algoritm:", e);
        }
        d.reset();
        d.update(value.getBytes());
        byte[] digest = d.digest();
        String hexStr = "";
        for (byte aDigest : digest) {
            hexStr += Integer.toString((aDigest & 0xff) + 0x100, 16).substring(1);
        }

        return hexStr;
    }
}
