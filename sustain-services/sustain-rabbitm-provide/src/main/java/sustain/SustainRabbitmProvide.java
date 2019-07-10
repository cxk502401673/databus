package sustain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 发布/订阅模式 生产者
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SustainRabbitmProvide {

	public static void main(String[] args)  {
		SpringApplication.run(SustainRabbitmProvide.class, args);
	}
}
