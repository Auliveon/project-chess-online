package com.ChessOnline.services;

import com.ChessOnline.models.Player;

import java.util.*;

public class RegistrationList {
    private Map<String, Player> userMap = new HashMap<>();
    private Map<String, String> unicURL= new HashMap<>();

    public boolean add(String key, Player user, String URL) {
        List<Player> userlist = new ArrayList(userMap.values());
        if (userlist.stream().noneMatch(user1 -> user.getUsername().equalsIgnoreCase(user1.getUsername())) && user != null) {
            this.userMap.put(key, user);
            this.unicURL.put(URL, key);
            System.out.println("=================================" + '\n' +
                    "Регистрируется пользователь:" + '\n' +
                    "Username: " + user.getUsername() + '\n' +
                    "Password: " + user.getPassword() +  '\n' +
                    "Email: " + user.getEmail() + '\n' +
                    "Activation code: " + key + '\n' +
                    "User map size: " + userMap.size() + '\n' +
                    "URL size: " + unicURL.size() + '\n' +
                    "=================================");

            return true;
        } else {
            return false;
        }
    }

    public Player getUserByActivationCode(String activationcode) {
        if(userMap.containsKey(activationcode)) return this.userMap.get(activationcode);
        else return null;
    }
    public String getActivationCodeByURL(String URL) {
        if(unicURL.containsKey(URL)) return this.unicURL.get(URL);
        else return null;
    }

    public Map<String, Player> getUserMap() {
        return userMap;
    }
    public void removeUser(String activationCode, String URL) {
        userMap.remove(activationCode);
        unicURL.remove(activationCode);
    }
}
