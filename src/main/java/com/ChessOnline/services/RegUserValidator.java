package com.ChessOnline.services;

import com.ChessOnline.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class RegUserValidator {
    private String username;
    private String password;
    private String password2;
    private String email;

    public RegUserValidator(String username, String password, String password2, String email) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }

    public boolean checkUsername(String username) {
        if(username.length() > 20 | username.length() < 3) return  false;


        char[] charNameArray = username.toCharArray();
        for(char element:charNameArray) {
            if(!((int)element > 64 & (int)element < 91 | (int)element > 96 & (int)element < 123)) return false;
        }


        List<Player> usersList;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Player.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String usernamef = "'" + username + "'";
        usersList = session.createQuery("from Player " + "where username = " + usernamef).getResultList();
        session.getTransaction().commit();
        factory.close();
        if (usersList.size() > 0) {
            if (usersList.get(0).getUsername().equalsIgnoreCase(username)) return false;
            else return true;
        }
        return true;
    }

    public boolean checkPassword(String password, String password2) {
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        if(!password.equals(password2)) return false;
        if(password.length() > 20 | password.length() < 8) return false;
        char[] passwordArray = password.toCharArray();
        for(char element:passwordArray){
            if(!((int)element > 40 & (int) element < 177)) {
                return false;
            }
            if((int)element > 64 & (int)element < 91) hasUpperCase = true;
            if((int)element > 96 & (int)element < 123) hasLowerCase = true;
            if((int)element > 47 & (int)element < 58) hasNumber = true;
        }
        if(hasLowerCase & hasUpperCase & hasNumber) return true;
        else return false;
    }

    public boolean checkEmail() {
        return false;

    }
}
