package com.weixin.commons;

import org.springframework.stereotype.Component;

@Component
public class Crypt {
    public static String enCrypt(String password) {
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= (i + 8);
            chars[i] = (char) (chars[i] - 2 * i + 14);
        }
        return new String(chars);
    }

    public static String deCrypt(String password) {
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + 2 * i - 14);
            chars[i] ^= (i + 8);
        }
        return new String(chars);
    }
}
