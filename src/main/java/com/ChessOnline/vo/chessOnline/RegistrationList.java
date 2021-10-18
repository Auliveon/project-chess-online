package com.ChessOnline.vo.chessOnline;

import com.ChessOnline.game.Player;
import com.ChessOnline.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationList {
    private List<RegInfo> regInfoList = new ArrayList<>();

    public boolean add(RegInfo regInfo) {
        if (regInfoList.stream().noneMatch(user1 -> regInfo.getLogin().equalsIgnoreCase(user1.getLogin())) && regInfo != null) {
            regInfoList.add(regInfo);
            System.out.println("=================================" + '\n' +
                    "Регистрируется пользователь:" + '\n' +
                    "Username: " + regInfo.getLogin() + '\n' +
                    "Password: " + regInfo.getPassword() +  '\n' +
                    "Email: " + regInfo.getEmail() + '\n' +
                    "Activation code: " + regInfo.getActivationCode() + '\n' +
                    "User map size: " + regInfoList.size() + '\n' +
                    "URL size: " + regInfoList.size() + '\n' +
                    "=================================");

            return true;
        } else {
            return false;
        }
    }

    public RegInfo getUserByActivationCode(String activationCode) {
        if(regInfoList.stream().anyMatch(regInfo -> regInfo.getActivationCode().equals(activationCode))) {
            return regInfoList.stream().filter(regInfo -> regInfo.getActivationCode().equals(activationCode)).findFirst().get();
        }
        else return null;
    }


    public List<RegInfo> getUserMap() {
        return this.regInfoList;
    }
    public void removeUser(String activationCode) {
        regInfoList.removeIf(regInfo -> regInfo.getActivationCode().equals(activationCode));
    }
}
