package com.ChessOnline.repository;

import com.ChessOnline.models.Role;
import com.ChessOnline.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByLogin(String login);

    @Query("select r from Role r")
    List<Role> getAllRolesForUser();
}
