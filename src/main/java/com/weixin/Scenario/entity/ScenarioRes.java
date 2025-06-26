package com.weixin.Scenario.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class ScenarioRes {
    private static final long serialVersionUID = 1L;

    private String sId;

    private String name;

    private String iconId;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Operate> operate;

    private String mode;

    private String author;

    private String nickName;

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
