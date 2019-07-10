package com.zjydt.sustain.common.entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * mq消息
 */
@Data
public class Message {

    /**
     *  消息唯一标识
     */

    private String id;
    /**
     *   消息产生的时间
      */


    private long timestamp;
    /**
     * 消息体内容
     */
    private JSONArray msgs;
    /**
     * routingkey
     */
    private String key;
    /**
     * exchange
     */
    private String exchange;
    /**
     * 消息过期时间（秒）
     */
    private Long overTime;

    /**
     * 是否返回消息体内容， true 返回，false 只返回该消息的id
     */
    private Boolean  returnMsg=false;
}
