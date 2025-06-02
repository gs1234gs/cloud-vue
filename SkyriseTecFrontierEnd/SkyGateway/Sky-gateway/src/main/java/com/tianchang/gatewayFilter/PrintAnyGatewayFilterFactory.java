package com.tianchang.gatewayFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;


import java.util.List;

public class PrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<PrintAnyGatewayFilterFactory.Config> {
//    @Override
//    public GatewayFilter apply(Config config) {
//        return new OrderedGatewayFilter(new GatewayFilter() {
//
//            @Override
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                System.out.println("打印过滤器");
//                return chain.filter(exchange);
//            }
//        },0);
//    }
@Override
public GatewayFilter apply(Config config) {
    return new OrderedGatewayFilter(
        ( exchange, chain)  -> {
            System.out.println("打印过滤器");
            return chain.filter(exchange);
        }
    ,0);
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public static class Config{
    private String base;
    private String header;
}
public PrintAnyGatewayFilterFactory(){
    super(Config.class);
}
    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("base","header");
    }
}
