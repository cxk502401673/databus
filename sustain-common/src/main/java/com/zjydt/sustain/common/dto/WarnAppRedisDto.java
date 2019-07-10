package com.zjydt.sustain.common.dto;

import lombok.Data;

@Data
public class WarnAppRedisDto {
    private String deployCode;
    private String warnAppName;
    private String warnAppCode;
    private String topicExchange;
    private String queueName;
}
