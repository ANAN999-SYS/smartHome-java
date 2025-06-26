package com.weixin.Record.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

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
@TableName("record")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String rId;

    /**
     * 操作者(openId)
     */
    private String author;

    /**
     * 详细操作
     */
    private String operate;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 操作设备类型
     */
    private String deviceType;
    /**
     * 操作设备房间
     */
    private String deviceRoom;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
