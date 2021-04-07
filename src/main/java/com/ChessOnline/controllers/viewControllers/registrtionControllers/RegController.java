package com.ChessOnline.controllers.viewControllers.registrtionControllers;

import com.ChessOnline.models.Player;
import com.ChessOnline.services.CurrentUserOnlineChecker;
import com.ChessOnline.services.Email.MailSender;
import com.ChessOnline.services.RegUserValidator;
import com.ChessOnline.services.RegistrationList;
import com.ChessOnline.services.SQL.SQLWriter;
import com.ChessOnline.services.generators.ActivationCodeGenerator;
import com.ChessOnline.services.generators.URLGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/register/")
public class RegController {
    RegistrationList userList = new RegistrationList();


    @Autowired
    MailSender mailSender;


    @GetMapping("/")
    public String register() {
        if (CurrentUserOnlineChecker.online()) return "redirect:/";
        else return "registrationTemplates/register";
    }

    @PostMapping("/")
    public String registerPost(Model model, HttpServletRequest request) {
        boolean isFreeUsername = true, isValidPassword = true, isValidEmail = true;
        model.addAttribute("isValidUsername", isFreeUsername);
        model.addAttribute("isValidPassword", isValidPassword);
        String username = request.getParameter("username_reg");
        String password = request.getParameter("password_reg");
        String password2 = request.getParameter("password_reg2");
        String email = request.getParameter("Email_reg");
        RegUserValidator rgv = new RegUserValidator(username, password, password2, email);
        String activationCode = new ActivationCodeGenerator().generateActivationCode();

        String unicUrl = new URLGenerator().generateURL(15);

        if (!rgv.checkUsername(username)) {
            isFreeUsername = false;
            model.addAttribute("isValidUsername", isFreeUsername);
            return "registrationTemplates/register";
        }

        if (!rgv.checkPassword(password, password2)) {
            isValidPassword = false;
            model.addAttribute("isValidPassword", isValidPassword);
            return "registrationTemplates/register";
        }

        isFreeUsername = userList.add(activationCode, new Player(username, new BCryptPasswordEncoder(8).encode(password), email), unicUrl);

        if (!isFreeUsername) {
            model.addAttribute("isValidUsername", isFreeUsername);
            return "registrationTemplates/register";
        }

        mailSender.send(email, activationCode);
        return "redirect:/register/emailValidator" + "?" +"URL=" + unicUrl + "&" +"err=" + true;
    }
    @GetMapping("/emailValidator")
    public String emailCheckerGet(@RequestParam("URL") String URL, @RequestParam("err") boolean err, Model model) {
        Player user = userList.getUserByActivationCode(userList.getActivationCodeByURL(URL));
        model.addAttribute("URL", URL);
        model.addAttribute("isExistUser", err);
        if(user != null) {
            return "registrationTemplates/emailValidator";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/emailValidator")
    public String emailCheckerPost(@RequestParam("URL") String URL, Model model, HttpServletRequest request) {
        boolean isExistUser = true;
        String activationCode = request.getParameter("code");
        Player user = userList.getUserByActivationCode(activationCode);

        if (user != null) {
            SQLWriter.writeNewUser(user);
            userList.removeUser(activationCode, URL);
            return "redirect:/login";
        } else {
            isExistUser = false;
            model.addAttribute("isExistUser", isExistUser);
            return "redirect:/register/emailValidator" + "?" +"URL=" + URL + "&" +"err=" + false;
        }

    }

}
