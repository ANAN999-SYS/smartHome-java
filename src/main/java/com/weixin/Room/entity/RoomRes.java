package com.weixin.Room.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomRes {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String roomId;

    /**
     * 名称
     */
    private String name;

    /**
     * 添加者id
     */
    private String author;

    /**
     * 添加者
     */
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
