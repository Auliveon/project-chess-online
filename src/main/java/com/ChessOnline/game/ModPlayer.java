package com.ChessOnline.game;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModPlayer {
    private String userName;
    private String side;


    public ModPlayer(HttpServletRequest request) {
        this.userName = request.getRemoteUser();

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
