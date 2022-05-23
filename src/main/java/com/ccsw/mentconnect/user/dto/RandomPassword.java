package com.ccsw.mentconnect.user.dto;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;

public class RandomPassword {

    @Value("${user.password.length}")
    private static int length;

    public static String generatePasswordSha256() {

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(RandomStringUtils.random(length, chars));
    }
}
