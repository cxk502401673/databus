package com.zjydt.sustain.common.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 人员
 * </p>
 *
 * @author lee_bw
 * @since 2018-10-08
 */
@Data
@Accessors(chain = true)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 地址
     */
    private String address;

    private String rountingKey;
    public Person(String rountingKey,String idCard){

        this.rountingKey=rountingKey;
        this.idCard=idCard;
    }
    public Person(String rountingKey,String idCard,String name){
        this.rountingKey=rountingKey;
        this.idCard=idCard;
        this.name=name;
    }
}
