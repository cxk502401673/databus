package com.zjydt.sustain.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeployData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type = IdType.UUID)
    private String id;
    /**
     *  身份证号/车牌/手机
     */
    private String idNum;
    /**
     *  感知应用代码
     */
    private String perceptCode;
    /**
     *  布控应用代码
     */
    private String deployCode;
    /**
     *  布控应用jsonStr
     */
    private String jsonStr;
    /**
     *  布控开始时间
     */
    private String startTime;
    /**
     *  布控结束时间
     */
    private String endTime;
    /**
     *  布控状态 N：正常 D：撤控
     */
    private String status;
}
