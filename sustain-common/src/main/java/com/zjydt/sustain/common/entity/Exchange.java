package com.zjydt.sustain.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * rabbitmq交换器
 */
@Data
public class Exchange implements Serializable {
    private String exchange;
    private String type;
    //如果为true，则表示仅在未使用时才删除该交换
    private Boolean del;
    //是否持久化  默认为 true表示持久化
    private Boolean durable=true;

    private Map<String,Object> args;
}
