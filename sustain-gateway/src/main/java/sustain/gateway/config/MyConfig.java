package sustain.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sustain.gateway.filter.SetTokenFilter;

@Configuration
public class MyConfig {
    @Bean
    public SetTokenFilter setTokenFilter() {
        return new SetTokenFilter();
    }
}
