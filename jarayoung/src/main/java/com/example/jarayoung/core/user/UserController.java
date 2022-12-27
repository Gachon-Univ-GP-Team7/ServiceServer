package com.example.jarayoung.core.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserProvider userProvider;

    public UserController(UserService userService, UserProvider userProvider) {
        this.userService = userService;
        this.userProvider = userProvider;
    }

    @GetMapping("info/{userIdx}")
    @RequestBody
    public getUserInfo(@PathVariable("userIdx") int userIdx)
}
