package com.example.jarayoung.core.auth.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostLoginRes {
    private boolean loginSuccess;
    private int userIdx;
}
