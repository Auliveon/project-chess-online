package com.ChessOnline.game;

import java.util.ArrayDeque;

public class UniqueUserQueue {
    static ArrayDeque<ModPlayer> userQueue = new ArrayDeque<>();

    static boolean uniqueAdd(ModPlayer player) {
        if(userQueue.stream().filter(elem -> elem.getUserName().equals(player.getUserName())).count() == 0){
            userQueue.add(player);
            if(userQueue.size() % 2 == 0) UniqueSessions.uniqueAdd(userQueue.poll(), userQueue.poll());
            return true;
        } else {
            return false;
        }
    }
    static boolean checkModPlayerInUserQueue(ModPlayer player) {
        if(userQueue.stream().filter(elem -> elem.getUserName().equals(player.getUserName())).count() == 0) {
            return false;
        }
        else return true;
    }

}


