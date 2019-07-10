package com.zjydt.sustain.common.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Queue {
    // 队列名称
    private String queueName;
    // 该队列消息过期时间
    private Long overTime;
    // 其他参数
    // 比如限制队列最大消息数量， key 为 x-max-length  value 为 数字
    //限制队列最大容量（字节数）  x-max-length-bytes   value 为 数字
    private Map<String,Object> args;
}
