package com.ChessOnline.util;

import com.ChessOnline.game.Answer;
import com.ChessOnline.game.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayDeque;

public class UniqueUserQueue {
    public static ArrayDeque<Player> userQueue = new ArrayDeque<>();

    public static boolean uniqueAdd(Player player) {
        if(userQueue.stream().filter(elem -> elem.getUserName().equals(player.getUserName())).count() == 0){
            userQueue.add(player);
            if(userQueue.size() % 2 == 0) UniqueSessions.uniqueAdd(userQueue.poll(), userQueue.poll());
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkModPlayerInUserQueue(Player player) {
        if(userQueue.stream().filter(elem -> elem.getUserName().equals(player.getUserName())).count() == 0) {
            return false;
        }
        else return true;
    }
    public static void handler(String request, String username, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(new Answer(null, null, "waiting",
                null, null, null)));

    }

}


