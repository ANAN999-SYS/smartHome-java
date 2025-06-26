package com.weixin.Feedback.service;

import com.weixin.Feedback.entity.UserFeedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.commons.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
public interface IUserFeedbackService extends IService<UserFeedback> {

    Result feedback(UserFeedback userFeedback);
    Result uploadImg(MultipartFile imgFile);

    Result getFeedback(String openId);

    Result getFeedbackAll();
    Result deleteFeedbackData(UserFeedback userFeedback);
    Result deleteFeedbacks(List<UserFeedback> userFeedbackList);

    Result checkFeedbacks(UserFeedback userFeedback);
}
