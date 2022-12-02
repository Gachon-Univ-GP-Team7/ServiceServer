package com.example.jarayoung.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
