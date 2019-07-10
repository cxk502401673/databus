package com.zjydt.sustain.common.entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;


@Data
public class PushData {
    /**
     * perceptJsonString : //感知类数据的数据体
     * jsonstring : //杂项
     * perceptCode : 感知类型 code
     * deployCode :  布控app code
     * touchTime : 触网时间戳
     * idNum : 330721
     */
    private String id; //该消息唯一标识

    private String key; // 队列名称

    private JSONArray msgs;

    private Long timestamp;


////////////////////////////////////////
    private String perceptJsonStr;

    private String perceptCode;
    private String deployCode;
    private String jsonStr;
    private String startTime;
    private String endTime;
    private Long touchTime;
    private String idNum;



    private String exchangeName;
    /**
     * 消息过期时间（秒）
     */
    private Long overTime;
}
