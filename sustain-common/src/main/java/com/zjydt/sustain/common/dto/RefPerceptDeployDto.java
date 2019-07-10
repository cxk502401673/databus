package com.zjydt.sustain.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RefPerceptDeployDto {
    @TableId(value="id", type = IdType.UUID)
    private String id;
    private String perceptCode;
    private String deployId;
    private String deployCode;
}
