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

    @GetMapping()
    public String home() {
        return "game/index";
    }

    @GetMapping("/findgame")
    public String findGame() {
        return "game/findGame";
    }
    @PostMapping("/getmodal")
    public ModelAndView getModal() {
        ModelAndView model = new ModelAndView("game/modal/findingGame");
        return model;
    }

    @GetMapping("/game")
    public String game(HttpServletRequest request) {
        if(UniqueSessions.checkModPlayerInSessionList(new Player(request))) {
            return "game/game";
        }
        else return "game/findGame";
    }

    @PostMapping("/register/sendActivationCode")
    public ResponseEntity<?> sendEmail(@RequestBody RegInfo regInfo) {
        String activationCode = ActivationCodeGenerator.generateActivationCode();
        mailSender.send(regInfo.getEmail(), activationCode);
        regInfo.setActivationCode(activationCode);
        userList.add(regInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "registrationTemplates/users";
    }

    @GetMapping("/users/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String blockUser(@RequestParam("login") String login, Model model) {
        userService.blockUser(login);
        model.addAttribute("users", userService.getAllUsers());
        return "registrationTemplates/users :: user_list";
    }

    @GetMapping("/users/unBlock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String unBlockUser(@RequestParam("login") String login, Model model) {
        userService.unBlockUser(login);
        model.addAttribute("users", userService.getAllUsers());
        return "registrationTemplates/users :: user_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/delete")
    public String removeUser(@RequestParam("login") String login, Model model) {
        userService.deleteUserByLogin(login);
        model.addAttribute("users", userService.getAllUsers());
        return "registrationTemplates/users :: user_list";
    }

    @PostMapping("/register/completeRegister")
    public ResponseEntity<?> completeRegister(@RequestBody RegInfo regInfo) {
        if(userList.getUserByActivationCode(regInfo.getActivationCode()) != null && !userService.getUserByLogin(regInfo.getLogin()).isPresent()
               // && RegUserValidator.check(regInfo.getLogin(), regInfo.getPassword(), regInfo.getPasswordRepeat(), regInfo.getEmail())
        ) {
            userService.createNewUser(new User(regInfo.getLogin(), regInfo.getPassword(), regInfo.getEmail()));
            return ResponseEntity.ok().build();
        } else {
           return new ResponseEntity<>("error", HttpStatus.NOT_ACCEPTABLE);
        }
    }



}




