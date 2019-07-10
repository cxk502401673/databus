package com.zjydt.sustain.common.entity;

import lombok.Data;

import java.io.Serializable;


//统一返回值,可描述失败细节
@Data
public class DetailRes<T> implements Serializable {
    boolean success;
    String errMsg;
    private T data;

    public DetailRes(boolean success, String errMsg, T data){
        this.success=success;
        this.errMsg=errMsg;
        this.data=data;
    }

    public DetailRes(boolean success, String errMsg) {
        this.success=success;
        this.errMsg=errMsg;
    }
}
