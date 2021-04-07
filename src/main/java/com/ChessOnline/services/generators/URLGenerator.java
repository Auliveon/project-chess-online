package com.ChessOnline.services.generators;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Random;
public class URLGenerator {
    @ModelAttribute("gggg")
    public String generateURL(int length) {
        Random rnd = new Random();
        StringBuilder activationCode = new StringBuilder();
        for(int i = 0; i < length; i++) {
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
