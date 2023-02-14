package com.ves.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class EncoderMd5 {

    public static String Md5(String account) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newStr = base64en.encode(md5.digest(account.getBytes("utf8")));
        String newStrs = base64en.encode(md5.digest(newStr.getBytes("utf8")));
        return newStrs;
    }
}
