package com.weixin.Scenario.entity;

import lombok.Data;

@Data
public class Operate {
    private String secretKey;

    private String name;

    private String author;

    private String roomName;

    private String topic;

    private boolean operate;

    private String type;
}
