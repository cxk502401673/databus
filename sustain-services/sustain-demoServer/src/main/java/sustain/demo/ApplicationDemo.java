package sustain.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication

public class ApplicationDemo {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ApplicationDemo.class, args);
    }
}
