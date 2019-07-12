package sustain.gateway.filter;

import com.zjydt.sustain.common.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Description 全局过滤器 在这里可以实现记录日志和访问权限校验等
 *
 * 通过网关的请求统一添加token 提供下游验证
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {
    private final static String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private final static String TOKEN = "token";
    private static final String BEARER = "Bearer";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        Map<String,Object > claim=new HashMap<>();
        claim.put("username",username);

        String token= JwtUtil.buildJWT(claim,username);

        //向headers中放文件，记得build
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder builder = request.mutate();

        builder.header(TOKEN, token);
        return chain.filter(exchange.mutate().request(builder.build()).build());

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
