package com.zjydt.sustain.common.exception;

/**
 * @author: lee_bw
 * @date: 2018/10/12 11:03
 * @description:
 */
public class PermissionDefinedException extends RuntimeException {

    public PermissionDefinedException() {
        super("权限不足，访问失败");
    }
}
