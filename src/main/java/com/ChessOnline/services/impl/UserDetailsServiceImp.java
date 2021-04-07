package com.ChessOnline.services.impl;

import com.ChessOnline.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
        UserBuilder builder = null;
        if(user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRole());
            builder.accountLocked(user.getEnabled() == 0);
        } else {
            throw new UsernameNotFoundException("");
        }
        return builder.build();
    }
}
