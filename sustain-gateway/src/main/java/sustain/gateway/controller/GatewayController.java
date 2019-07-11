//package sustain.gateway.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//import sustain.gateway.service.DynamicRouteService;
//
//import java.net.URI;
//
//@Slf4j
//@RestController
//public class GatewayController {
//    @Autowired
//    DynamicRouteService dynamicRouteService;
//
//    @RequestMapping(value = "/reflushGatewayRoutes")
//    public void reflushGatewayRoutes(){
//        dynamicRouteService.notifyChanged();
//
//    }
//
//    @RequestMapping(value = "/addGatewayRoute")
//    public void addGatewayRoute(){
//        RouteDefinition definition = new RouteDefinition();
//        definition.setId("id");
//        URI uri = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8888/test").build().toUri();
//        // URI uri = UriComponentsBuilder.fromHttpUrl("http://baidu.com").build().toUri();
//        definition.setUri(uri);
//
//        dynamicRouteService.add(definition);
//
//    }
//
//}
