package com.zjydt.sustain.sentinel;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class init implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${sentinel.remoteAddress}")
    private String remoteAddress;
    @Value("${sentinel.remoteAddress}")
    private String groupId;
    @Value("${sentinel.remoteAddress}")
    private String dataId;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("remoteAddress:"+remoteAddress);
        log.info("groupId:"+groupId);
        log.info("dataId:"+dataId);
        Converter<String, List<FlowRule>> parser =
                source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){});
        ReadableDataSource<String ,List<FlowRule> >nacosDataSource=

                new NacosDataSource<>(remoteAddress,groupId,dataId,parser);

        FlowRuleManager.register2Property(nacosDataSource.getProperty());

        log.info("====init sentinel ok ====");

    }
}
