package com.ChessOnline.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class CurrentUserOnlineChecker {

    public static  boolean online() {
        try {
            User user;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.getPrincipal();
            if (!user.getUsername().equals(null)) return true;
        } catch (ClassCastException e) {
            return false;
        }
        return false;
    }
}
