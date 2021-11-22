package com.gomsk.project.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static com.gomsk.project.api.service.LoginService.LOGIN_SESSION_KEY;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, HttpSession session,
                        @RequestParam(required = false) String redirect){
        model.addAttribute("isSignIn", session.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("redirect", redirect);
        System.out.println("xx :" + model.toString());
        return "index";
    }
}
