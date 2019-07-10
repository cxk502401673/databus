package sustain.gateway.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
//配置前缀
@ConfigurationProperties(prefix = "gateway.filter")
//配置文件名
@PropertySource("classpath:/gateway-filter-uri.properties")
@Data
public class PropertiesConfig {
    //前缀名（gateway.filter） + 后缀名（uri）
    private String uri;

    //将定义的uri转化成list
    public List<String> handleUri() {
        return Arrays.asList(uri.split(","));
    }
}
