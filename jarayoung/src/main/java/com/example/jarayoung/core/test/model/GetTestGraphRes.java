package com.example.jarayoung.core.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTestGraphRes {
   private List<TestGraphListData> voiceGraphList;
   private List<TestGraphListData> videoGraphList;
}


