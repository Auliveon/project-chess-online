package com.ChessOnline.security;

import com.ChessOnline.exception.UserBlockedException;
import com.ChessOnline.model.User;
import com.ChessOnline.service.db.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;

    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user.getStatus().toString().equals("BANE")) {
            throw new UserBlockedException("User was blocked");
        }
        return SecurityUser.fromUser(user);
    }
}
