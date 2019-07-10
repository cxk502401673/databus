package com.zjydt.sustain.common.entity;

import lombok.Data;

import java.io.Serializable;
@Data
/**
 * 校验JWT
 *
 *   claimsJws   jwt 内容文本
 *    sub       jwt 面向的用户

 */
public class TokenData  implements Serializable {
    String claimsJws;
    String sub;
}
