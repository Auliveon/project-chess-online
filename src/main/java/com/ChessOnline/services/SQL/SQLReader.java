package com.ChessOnline.services.SQL;

import com.ChessOnline.models.Player;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.ModelAttribute;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

    public class SQLReader {
        @ModelAttribute("read")
        public static Player getUserByUsername(String username) {
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Player.class).buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            String usernamef = "'" + username + "'";
            Player user;
            try {
                user = (Player) session.createQuery(" from Player " + "where username = " + usernamef).getSingleResult();
            } catch (Exception e) {
                System.out.println("User with username " + usernamef + " not found");
                user = null;
            }
            session.getTransaction().commit();
            factory.close();
            return user;
        }
    }

