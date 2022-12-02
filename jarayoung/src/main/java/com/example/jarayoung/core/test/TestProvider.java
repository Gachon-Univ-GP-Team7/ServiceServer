package com.example.jarayoung.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TestProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TestDao testDao;

    public TestProvider(TestDao testDao) {
        this.testDao = testDao;
    }
}
