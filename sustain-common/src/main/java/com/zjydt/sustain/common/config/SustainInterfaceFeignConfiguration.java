package com.zjydt.sustain.common.config;

import com.zjydt.sustain.common.interfaceService.service.NationResidentRequestServiceFallback;
import org.springframework.context.annotation.Bean;

public class SustainInterfaceFeignConfiguration {
    @Bean
    public NationResidentRequestServiceFallback nationResidentRequestServiceFallback() {
        return new NationResidentRequestServiceFallback();
    }

}
