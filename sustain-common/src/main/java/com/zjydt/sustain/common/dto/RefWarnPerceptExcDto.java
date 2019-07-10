package com.zjydt.sustain.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class RefWarnPerceptExcDto implements Serializable {
    @TableId(value="id", type = IdType.UUID)
    private String id;
    private String perceptCode;
    private String warnAppCode;
}
