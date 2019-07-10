package com.zjydt.sustain.common.dto;

import lombok.Data;


@Data
public class DeployDetailDto {
    private String jsonStr;
    private String startTime;
    private String endTime;

    public DeployDetailDto() {
    }

    public DeployDetailDto(String jsonStr, String startTime, String endTime) {
        this.jsonStr = jsonStr;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
