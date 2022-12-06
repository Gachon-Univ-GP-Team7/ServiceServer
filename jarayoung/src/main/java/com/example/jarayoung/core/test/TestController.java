package com.example.jarayoung.core.test;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BaseResponse;
import com.example.jarayoung.core.test.model.GetTestListRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/")
public class TestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TestService testService;

    @Autowired
    private final TestProvider testProvider;

    public TestController(TestService testService, TestProvider testProvider) {
        this.testService = testService;
        this.testProvider = testProvider;
    }


    @GetMapping("testList/{userIdx}")
    @ResponseBody
    public BaseResponse<GetTestListRes> getTestList(@PathVariable("userIdx") int userIdx) {

        try{
            return new BaseResponse<>(testProvider.getTestList(userIdx));
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
