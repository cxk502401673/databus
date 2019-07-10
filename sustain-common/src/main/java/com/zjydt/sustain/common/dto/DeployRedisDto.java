package com.zjydt.sustain.common.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DeployRedisDto {
    /**
     *  身份证号/手机号/车牌号等
     * */
    //private String idNum;


    private Map<String, PerceptRedisDto> perceptMap;

    public DeployRedisDto() {
    }

    public DeployRedisDto(Map<String, PerceptRedisDto> perceptMap) {
        this.perceptMap = perceptMap;
    }

}
