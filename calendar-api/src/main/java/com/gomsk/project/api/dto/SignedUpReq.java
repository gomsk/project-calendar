package com.gomsk.project.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignedUpReq {
    private final String name;
    private final String email;
    private final String password;
    private final LocalDate birthday;
}
