package sustain.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;


@EnableZuulProxy
@SpringBootApplication
public class SustainZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SustainZuulApplication.class, args);
	}
}
