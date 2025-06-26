package com.weixin.Feedback.mapper;

import com.weixin.Feedback.entity.UserFeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@Mapper
@Resource
public interface UserFeedbackMapper extends BaseMapper<UserFeedback> {
    @Delete("Delete from user_feedback where f_id=#{fId}")
    void deleteByFid(String fId);
}
