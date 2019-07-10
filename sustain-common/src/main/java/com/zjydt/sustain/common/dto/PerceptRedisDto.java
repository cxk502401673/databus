package com.zjydt.sustain.common.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PerceptRedisDto {

    //String: perceptCode;

    private Map<String, DeployDetailDto> deployMap;

    public PerceptRedisDto(){

    }
    public PerceptRedisDto(Map<String, DeployDetailDto> deployMap) {
        this.deployMap = deployMap;
    }
}
