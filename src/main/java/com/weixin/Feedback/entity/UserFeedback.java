package com.weixin.Feedback.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user_feedback",autoResultMap = true)
public class UserFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Fid
     */
    private String fId;
    /**
     * 反馈人(openId)
     */
    private String author;
    /**
     * 反馈人头像
     */
    private String avatarUrl;
    /**
     * 反馈人昵称
     */
    private String nickname;

    /**
     * 反馈消息
     */
    private String feedbackMsg;

    /**
     * 图片地址
     */

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ImgData> imageUrl;

    /**
     * 处理状态
     */
    private String state;

    /**
     * 处理人
     */
    private String adminId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
