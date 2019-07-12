package sustain.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心
@EnableDiscoveryClient
public class ApplicationGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationGateway.class, args);
    }

}
