package com.gomsk.project.api.controller;

import com.gomsk.project.api.dto.LoginReq;
import com.gomsk.project.api.dto.SignedUpReq;
import com.gomsk.project.api.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignedUpReq signUpReq, HttpSession session){
        loginService.signUp(signUpReq, session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(@Valid  @RequestBody LoginReq loginReq, HttpSession session){
        loginService.login(loginReq, session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> login(HttpSession session){
        loginService.logout(session);
        return ResponseEntity.ok().build();
    }


}
