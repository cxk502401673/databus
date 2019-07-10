package com.zjydt.sustain.common.dto;

import lombok.Data;

@Data
public class CallBackRedisDto {
    private String serviceName;
    private String methodName;

    private String redisKey;
    private String itemKey;
    //private String deployCode;
    //private String typeCode;

    private String keyCode;//布控数据：deployCode 数据字典数据:typeCode
    //接口布控数据
    private DeployData[] deployRedisDatas;
    @Override
    public String toString() {
        return "CallBackRedisDto{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", redisKey='" + redisKey + '\'' +
                ", itemKey='" + itemKey + '\'' +
                ", keyCode='" + keyCode + '\'' +
                '}';
    }
}
