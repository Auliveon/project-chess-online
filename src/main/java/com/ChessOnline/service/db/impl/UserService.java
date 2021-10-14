package com.ChessOnline.service.db.impl;

import com.ChessOnline.model.Role;
import com.ChessOnline.model.User;

import com.ChessOnline.service.db.IUserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private SessionFactory sessionFactory;

    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, SessionFactory sessionFactory) {
        this.passwordEncoder = passwordEncoder;
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        return sessionFactory.getCurrentSession().createQuery(cq).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Role> cq = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = cq.from(Role.class);
        cq.select(roleRoot);
        return sessionFactory.getCurrentSession().createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByLogin(String login) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot).where(criteriaBuilder.equal(userRoot.get(User.CLN_USER_LOGIN), login));
        return sessionFactory.getCurrentSession().createQuery(cq).uniqueResultOptional();
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUserByLogin(String login) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
        Root<User> containerRoot = cd.from(User.class);
        cd.where(cb.equal(containerRoot.get(User.CLN_USER_LOGIN), login));
        sessionFactory.getCurrentSession().createQuery(cd).executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<User> filterUser(String filterText) {
        return Collections.emptyList();
    }
}
