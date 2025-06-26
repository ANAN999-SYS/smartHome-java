package com.weixin.Record.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordRes {
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
     * 操作者
     */
    private String nickName;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
