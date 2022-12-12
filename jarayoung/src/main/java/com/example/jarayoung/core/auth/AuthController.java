package com.example.jarayoung.core.auth;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BaseResponse;
import com.example.jarayoung.baseModels.BasicServerStatus;
import com.example.jarayoung.core.auth.model.PostApplyReq;
import com.example.jarayoung.core.auth.model.PostApplyRes;
import com.example.jarayoung.core.auth.model.PostLoginReq;
import com.example.jarayoung.core.auth.model.PostLoginRes;
import com.example.jarayoung.utils.RegxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    final RegxUtils RegxChecker = new RegxUtils();

    @Autowired
    private final AuthProvider authProvider;
    @Autowired
    private final AuthService authService;

    public AuthController(AuthProvider authProvider, AuthService authService){
        this.authProvider = authProvider;
        this.authService = authService;
    }

    /**
     * 회원가입 API
     * */

    @PostMapping("apply")
    @ResponseBody
    public BaseResponse<PostApplyRes> userApply(@RequestBody PostApplyReq postApplyReq){

        try{
            if(!RegxChecker.isValidEmail(postApplyReq.getEmail())){
                throw new BaseException(BasicServerStatus.NOT_A_EMAIL);
            }

            return new BaseResponse<>(authService.userApply(postApplyReq));
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("login")
    @ResponseBody
    public BaseResponse<PostLoginRes> userLogin(@RequestBody PostLoginReq postLoginReq){

        try{
            if(!RegxChecker.isValidEmail(postLoginReq.getEmail())){
                throw new BaseException(BasicServerStatus.NOT_A_EMAIL);
            }

            return new BaseResponse<>(authService.userLogin(postLoginReq));
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
