package com.zjydt.sustain.common.enums;

public enum StatusEnum {
    NORMAL("N", "正常"),

    PARSE("P","暂停") ,

    DELETE("D","删除");

    private String code;

    private String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
