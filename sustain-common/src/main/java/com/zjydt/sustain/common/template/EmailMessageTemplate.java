package com.zjydt.sustain.common.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lee_bw
 * @date: 2018/11/30 17:18
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailMessageTemplate extends MessageTemplate{

    /**
     * email地址
     */
    private String email;
}
