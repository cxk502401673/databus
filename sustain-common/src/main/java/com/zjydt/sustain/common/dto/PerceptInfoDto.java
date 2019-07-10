package com.zjydt.sustain.common.dto;

import lombok.Data;

@Data
public class PerceptInfoDto{
    private String idNum;
    private String perceptCode;
    private String perceptJsonStr;
    /**
     * 消息过期时间（秒）
     */
    private Long overTime;
}
