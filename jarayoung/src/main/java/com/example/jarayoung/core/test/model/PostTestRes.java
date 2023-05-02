package com.example.jarayoung.core.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostTestRes {
    private int testIdx;
    private String testDate;
    private float overallScore;
    private float label;
}
