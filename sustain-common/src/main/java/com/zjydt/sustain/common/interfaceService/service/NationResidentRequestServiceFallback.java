package com.zjydt.sustain.common.interfaceService.service;


import com.jhjss.infs.domain.PopDomain;
import com.zjydt.sustain.common.dto.InterfaceReqDto;

public class NationResidentRequestServiceFallback implements NationResidentRequestService {
    @Override
    public PopDomain getData(InterfaceReqDto dto) {
        return null;
    }
}
