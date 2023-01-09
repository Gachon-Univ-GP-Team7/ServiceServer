package com.example.jarayoung.core;

import com.example.jarayoung.baseModels.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example/")
public class ExampleController {
    @GetMapping("")
    @ResponseBody
    public BaseResponse<String> ExampleApi(){
        return new BaseResponse<>("Test");
    }
}
