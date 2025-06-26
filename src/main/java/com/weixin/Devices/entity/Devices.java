package com.weixin.Devices.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Devices implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */
    private String deId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String iconId;

    /**
     * 设备类型
     */
    private String type;

    /**
     * 所属房间Id
     */
    private String roomId;

    /**
     * 所属房间
     */
    private String roomName;

    /**
     * 类型码
     */
    private String code;

    /**
     * 订阅主题
     */
    private String topic;

    /**
     * 作者Id(openId)
     */
    private String author;

    /**
     * 状态
     */
    private String state;

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
