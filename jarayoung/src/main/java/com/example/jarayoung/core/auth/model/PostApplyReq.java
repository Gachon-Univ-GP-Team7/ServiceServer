package com.example.jarayoung.core.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostApplyReq {
    private String userName;
    private String infantName;
    private String infantBirthday;
    private String password;
    private String email;
}
