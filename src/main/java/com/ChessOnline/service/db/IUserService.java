package com.ChessOnline.service.db;

import com.ChessOnline.model.Role;
import com.ChessOnline.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void updateUserWins(String login);

    List<User> getAllUsers();

    List<Role> getAllRoles();

    User createNewUser(User user);

    User updateUser(User user);

    Optional<User> getUserByLogin(String login);

    void deleteUserByLogin(String login);
    
    List<User> filterUser(String filterText);

}
