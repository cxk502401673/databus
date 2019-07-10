package com.zjydt.sustain.common.dto;

import com.jhjss.infs.common.PageDomain;
import com.jhjss.infs.common.UserDomain;
import com.jhjss.infs.domain.PopDomain;

public class InterfaceReqDto {
    private UserDomain userDomain;
    private PopDomain popDomain;

    public UserDomain getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    public PopDomain getPopDomain() {
        return popDomain;
    }

    public void setPopDomain(PopDomain popDomain) {
        this.popDomain = popDomain;
    }

    public InterfaceReqDto(UserDomain userDomain, PopDomain popDomain) {
        this.userDomain = userDomain;
        this.popDomain = popDomain;
    }
}
