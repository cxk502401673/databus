//package sustain.gateway.filter;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.zjydt.sustain.common.util.JwtUtil;
//import io.netty.buffer.ByteBufAllocator;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.core.io.buffer.NettyDataBufferFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.util.Assert;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.io.InputStream;
//import java.net.URI;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * https://www.codercto.com/a/79061.html
// *
// *
// */
//@Slf4j
//@Data
//public class SetTokenFilter implements GlobalFilter, Ordered {
//
//    @Autowired
//    private PropertiesConfig propertiesConfig;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final DataBufferFactory dataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
//    private class InputStreamHolder {
//
//        InputStream inputStream;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        /**
//         *
//         *
//         * 1.获取请求信息
//         *
//         */
//        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
//        URI uri = gatewayUrl.getUri();
//        ServerHttpRequest request = (ServerHttpRequest)exchange.getRequest();
//        HttpHeaders header = request.getHeaders();
//        ServerHttpRequest.Builder mutate = request.mutate();
//
//        String rawPath = request.getURI().toString();
//        String path = request.getPath().value();
//        String method = request.getMethodValue();
//        String instance = uri.getAuthority();
//
//        System.out.println("*********************1.请求信息*******************");
//        System.out.println("RawPath:" + rawPath);
//        System.out.println("path:" + path);
//        System.out.println("method:" + method);
//        System.out.println("instance:" + instance);
//        System.out.println("****************************************");
//
//        // 新建一个ServerHttpRequest装饰器,覆盖需要装饰的方法
//        ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(request) {
//
//            @Override
//            public Flux<DataBuffer> getBody() {
//                Flux<DataBuffer> body = super.getBody();
//                InputStreamHolder holder = new InputStreamHolder();
//                body.subscribe(buffer -> holder.inputStream = buffer.asInputStream());
//                if (null != holder.inputStream) {
//                    try {
//                        // 解析JSON的节点
//                        JsonNode jsonNode = objectMapper.readTree(holder.inputStream);
//                        Assert.isTrue(jsonNode instanceof ObjectNode, "JSON格式异常");
//                        ObjectNode objectNode = (ObjectNode) jsonNode;
//                        // JSON节点最外层写入新的属性
//                        objectNode.put("userId", accessToken);
//                        DataBuffer dataBuffer = dataBufferFactory.allocateBuffer();
//                        String json = objectNode.toString();
//                        log.info("最终的JSON数据为:{}", json);
//                        dataBuffer.write(json.getBytes(StandardCharsets.UTF_8));
//                        return Flux.just(dataBuffer);
//                    } catch (Exception e) {
//                        throw new IllegalStateException(e);
//                    }
//                } else {
//                    return super.getBody();
//                }
//            }
//        };
//        // 使用修改后的ServerHttpRequestDecorator重新生成一个新的ServerWebExchange
//        return chain.filter(exchange.mutate().request(decorator).build());
//    }
//
//        //todo
//        String username = exchange.getRequest().getQueryParams().getFirst("username");
//        Map<String,Object > claim=new HashMap<>();
//        claim.put("username",username);
//        String token= JwtUtil.buildJWT(claim,username);
//
//        exchange.getRequest().getHeaders().set("token",token);
//
//
////        if (token == null || token.isEmpty()) {
////            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
////            return exchange.getResponse().setComplete();
////        }
//        return chain.filter(exchange);
//    }
//
//
//    @Override
//    public int getOrder() {
//        return -100;
//    }
//}
