package sustain.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sustain.gateway.filter.SetTokenFilter;

@Configuration
public class MyConfig {
    @Bean
    public SetTokenFilter setTokenFilter() {
        return new SetTokenFilter();
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/baidu")
//                        .uri("http://baidu.com:80/")
//                )
//                .route("websocket_route", r -> r.path("/apitopic1/**")
//                        .uri("ws://127.0.0.1:6605"))
//                .route(r -> r.path("/userapi3/**")
//                        .filters(f -> f.addResponseHeader("X-AnotherHeader", "testapi3"))
//
//                        .uri("lb://user-service/")
//                )
//                .build();
//    }
}
