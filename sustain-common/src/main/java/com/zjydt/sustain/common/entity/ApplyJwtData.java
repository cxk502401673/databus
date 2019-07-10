package com.zjydt.sustain.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 申请token 的参数
 *  *     @param sub      jwt 面向的用户
 *      * @param aud      jwt 接收方
 *      * @param jti      jwt 唯一身份标识
 *      * @param iss      jwt 签发者
 *      * @param nbf      jwt 生效日期时间
 *      * @param duration jwt 有效时间，单位：秒
 */
@Data

public class ApplyJwtData implements Serializable {
    String sub;
    Integer duration;
    Map<String,Object> claim;
}
