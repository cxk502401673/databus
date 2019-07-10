package com.zjydt.sustain.common.interfaceService.service;


import com.jhjss.infs.domain.PopDomain;
import com.zjydt.sustain.common.config.SustainInterfaceFeignConfiguration;
import com.zjydt.sustain.common.dto.InterfaceReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "sustain-interface-server", fallback = NationResidentRequestServiceFallback.class, configuration = SustainInterfaceFeignConfiguration.class)
public interface NationResidentRequestService {
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    PopDomain getData(@RequestBody InterfaceReqDto interfaceReqDto);


}
