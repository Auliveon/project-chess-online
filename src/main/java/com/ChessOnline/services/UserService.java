package com.ChessOnline.services;

import com.ChessOnline.models.Role;
import com.ChessOnline.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    List<Role> getAllRoles();

    User createNewUser(User user);

    User updateUser(User user);

    User getUserById(Long pid);

    Optional<User> getUserByLogin(String login);

    void deleteUserById(Long pid);
    
    List<User> filterUser(String filterText);

}
