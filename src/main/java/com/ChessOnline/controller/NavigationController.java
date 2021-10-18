package com.ChessOnline.controller;

import com.ChessOnline.game.Player;
import com.ChessOnline.model.User;
import com.ChessOnline.service.db.IUserService;
import com.ChessOnline.service.email.MailSender;
import com.ChessOnline.util.ActivationCodeGenerator;
import com.ChessOnline.util.RegUserValidator;
import com.ChessOnline.util.URLGenerator;
import com.ChessOnline.util.UniqueSessions;
import com.ChessOnline.vo.chessOnline.RegInfo;
import com.ChessOnline.vo.chessOnline.RegistrationList;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller

public class NavigationController {

    private final MessageSource messageSource;

    private final IUserService userService;

    private final MailSender mailSender;

    private final RegistrationList userList = new RegistrationList();

    public NavigationController(MessageSource messageSource, IUserService userService, MailSender mailSender) {
        this.messageSource = messageSource;
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @GetMapping("/register")
    public String registerGet() {
        return "registrationTemplates/register";
    }

    @GetMapping("/login")
    public String login() {
        return "registrationTemplates/login";
    }

    @PostMapping("/register/sendActivationCode")
    public ResponseEntity<?> sendEmail(@RequestBody RegInfo regInfo) {
        String activationCode = ActivationCodeGenerator.generateActivationCode();
        mailSender.send(regInfo.getEmail(), activationCode);
        regInfo.setActivationCode(activationCode);
        userList.add(regInfo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/completeRegister")
    public ResponseEntity<?> completeRegister(@RequestBody RegInfo regInfo) {
        if(userList.getUserByActivationCode(regInfo.getActivationCode()) != null) {
            userService.createNewUser(new User(regInfo.getLogin(), regInfo.getPassword(), regInfo.getEmail()));
            return ResponseEntity.ok().build();
        } else {
           return new ResponseEntity<>("error", HttpStatus.NOT_ACCEPTABLE);
        }
    }



}




