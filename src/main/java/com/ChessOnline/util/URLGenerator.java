package com.ChessOnline.util;

import java.util.Random;
public class URLGenerator {

    public static String generateURL(int length) {
        Random rnd = new Random();
        StringBuilder activationCode = new StringBuilder();
        for(int i = 0; i < length; i++) {
            ActivationCodeGenerator.test(rnd, activationCode);
        }
        return activationCode.toString();
    }
}
