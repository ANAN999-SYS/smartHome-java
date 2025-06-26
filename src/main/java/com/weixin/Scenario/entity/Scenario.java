package com.weixin.Scenario.entity;

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
 * @since 2024-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "scenario",autoResultMap = true)
public class Scenario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sId;

    private String name;

    private String iconId;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Operate> operate;

    private String mode;

    private String author;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
