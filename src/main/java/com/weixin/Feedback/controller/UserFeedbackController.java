package com.weixin.Feedback.controller;



import com.weixin.Feedback.entity.UserFeedback;
import com.weixin.Feedback.service.IUserFeedbackService;
import com.weixin.Utils.JWTUtils;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@CrossOrigin
@RestController
@RequestMapping("/Feedback")
public class UserFeedbackController {
    @Autowired
    private IUserFeedbackService iUserFeedbackService;
    @PostMapping("/feedback")
    public Result feedback(@RequestBody UserFeedback userFeedback){
        System.out.println(userFeedback);
        Result result=iUserFeedbackService.feedback(userFeedback);
        return result;
    }
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam(value = "imgFile") MultipartFile imgFile){
        Result result=iUserFeedbackService.uploadImg(imgFile);
        return result;
    }

    @GetMapping("/getFeedback")
    public Result getFeedback(@RequestHeader("Authorization") String token){
        String openId= JWTUtils.validateToken(token);
        Result result=iUserFeedbackService.getFeedback(openId);
        return result;
    }

    //后台
    @GetMapping("/getFeedbackAll")
    public Result getFeedbackAll(){
        Result result=iUserFeedbackService.getFeedbackAll();
        return result;
    }

    @PostMapping("/deleteFeedbackData")
    public Result deleteFeedbackData(@RequestBody UserFeedback userFeedback){
        Result result=iUserFeedbackService.deleteFeedbackData(userFeedback);
        return result;
    }
    @PostMapping("/deleteFeedbacks")
    public Result deleteFeedbacks(@RequestBody List<UserFeedback> userFeedbackList){
        Result result=iUserFeedbackService.deleteFeedbacks(userFeedbackList);
        return result;
    }
    @PostMapping("/checkFeedbacks")
    public Result checkFeedbacks(@RequestBody UserFeedback userFeedback){
        Result result=iUserFeedbackService.checkFeedbacks(userFeedback);
        return result;
    }
}

