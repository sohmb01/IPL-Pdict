package com.pdict.iplpredict.crypto;

import org.apache.commons.lang3.RandomStringUtils;

public class AccessTokenGenerator {

    public static String generateAccessToken(String username, String password) {
        String random = RandomStringUtils.randomAlphabetic(10);
        return SHA512HashGenerator.getSHA512Hash(username+password+random).substring(0,32);
    }
}
