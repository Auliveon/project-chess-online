package com.ChessOnline.services.generators;

import java.util.Random;

public class ActivationCodeGenerator {
    public String generateActivationCode() {
        Random rnd = new Random();
        StringBuilder activationCode = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            int type = rnd.nextInt(3);
            if(type == 0) {
                activationCode.append((char)(65 + rnd.nextInt(26)));
            }
            if(type == 1) {
                activationCode.append((char)(48 + rnd.nextInt(10)));
            }
            if(type == 2) {
                activationCode.append((char)(97 + rnd.nextInt(26)));
            }
        }
        return activationCode.toString();
    }
}
