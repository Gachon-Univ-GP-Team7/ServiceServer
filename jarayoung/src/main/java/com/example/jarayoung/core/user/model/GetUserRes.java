package com.example.jarayoung.core.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRes {
    private int userIdx;
    private String userName;
    private String email;
    private String babyName;
    private String babyBirthday;
}
