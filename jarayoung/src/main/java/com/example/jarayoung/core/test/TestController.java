package com.example.jarayoung.core.test;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BaseResponse;
import com.example.jarayoung.core.test.model.GetTestGraphRes;
import com.example.jarayoung.core.test.model.GetTestListRes;
import com.example.jarayoung.core.test.model.GetTestProgressView;
import com.example.jarayoung.core.test.model.GetTestScoreRes;
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


    /**
     * 테스트 기록 리스트 조회 API
     * */
    @GetMapping("testList/{userIdx}")
    @ResponseBody
    public BaseResponse<GetTestListRes> getTestList(@PathVariable("userIdx") int userIdx) {

        try{
            return new BaseResponse<>(testProvider.getTestList(userIdx));
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 테스트 기록 점수 그래프 조회 API
     * */
    @GetMapping("testGraph/{userIdx}")
    @ResponseBody
    public BaseResponse<GetTestGraphRes> getTestGraphList(@PathVariable("userIdx") int userIdx) {

        try{
            return new BaseResponse<>(testProvider.getTestGraphList(userIdx));
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 테스트 기록 상세조회 API (음성)
     * */
    @GetMapping("voiceTestRecord/{voiceTestIdx}")
    @ResponseBody
    public BaseResponse<GetTestScoreRes> getVoiceTestScore(@PathVariable("voiceTestIdx") int testIdx){

        try{
            return new BaseResponse<>(testProvider.getVoiceTestScore(testIdx));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 테스트 기록 상세조회 API (영상)
     * */
    @GetMapping("videoTestRecord/{videoTestIdx}")
    @ResponseBody
    public BaseResponse<GetTestScoreRes> getVideoTestScore(@PathVariable("videoTestIdx") int testIdx){

        try{
            return new BaseResponse<>(testProvider.getVideoTestScore(testIdx));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 테스트 진행 뷰 구성 API (통합 (모드 받아야함))
     * */
    @GetMapping("testView")
    @ResponseBody
    public BaseResponse<GetTestProgressView> getTestProgressView(@RequestParam int userIdx, int testMode){
        try{
            return new BaseResponse<>(testProvider.getTestProgressView(userIdx, testMode));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 테스트 진행 API -> 서비스에서 ML API에 접근해 데이터 받아야 함 (보류)
     * */

}
