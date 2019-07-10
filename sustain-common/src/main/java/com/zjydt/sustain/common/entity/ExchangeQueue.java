package com.zjydt.sustain.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ExchangeQueue implements Serializable {
    private String exchange;
    private String type;
    //如果为true，则表示仅在未使用时才删除该交换
    private Boolean del;
    //是否持久化  默认为 true表示持久化
    private Boolean durable = true;
    //绑定的队列名
    private String queue;
    //路由key
    private String key;
    //队列中消息的过期时间(秒)
    private Long overTime;

    // 比如限制队列最大消息数量， key 为 x-max-length  value 为 数字
    //限制队列最大容量（字节数）  x-max-length-bytes   value 为 数字
    private Map<String,Object> args;





    public ExchangeQueue (String exchange,String type,String queue,String key){
        this.exchange=exchange;
        this.type=type;

        this.queue=queue;
        this.key=key;

    }
    public ExchangeQueue (String exchange,String type,Boolean del,Boolean durable,String queue,String key){
        this.exchange=exchange;
        this.type=type;
        this.del=del;
        this.durable=durable;
        this.queue=queue;
        this.key=key;

    }
}
