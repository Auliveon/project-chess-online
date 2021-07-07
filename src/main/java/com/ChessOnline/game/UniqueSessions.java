package com.ChessOnline.game;

import java.util.ArrayList;
import java.util.List;

public class UniqueSessions {
    public static List<Session> sessionList = new ArrayList<>();
    public static boolean uniqueAdd(Player player1, Player player2) {
        if(sessionList.stream().noneMatch(elem -> elem.getSessionPlayersNames().contains(player1.getUserName()))
            && sessionList.stream().noneMatch(elem -> elem.getSessionPlayersNames().contains(player2.getUserName()))) {
            sessionList.add(new Session(player1, player2));
            return true;
        } else {
            return false;
        }

    }

    public static Session getSessionByModPlayer(Player player) {
        for (Session session: sessionList) {
            if(session.getSessionPlayersNames().contains(player.getUserName())) return session;
        }
        return null;
    }

    public static boolean checkModPlayerInSessionList(Player player) {
        for(Session session: sessionList) {
            if(session.getSessionPlayersNames().contains(player.getUserName())) return true;
        }
        return false;
    }

}
