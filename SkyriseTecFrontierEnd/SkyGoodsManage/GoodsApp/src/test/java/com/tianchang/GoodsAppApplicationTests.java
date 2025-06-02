package com.tianchang;

import com.tianchang.configOpenfeign.client.OrdersClient;
import com.tianchang.mapper.ProductMapper;
import com.tianchang.service.ProductService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsAppApplicationTests {

    @Resource
    private ProductService productService;
    @Resource
    OrdersClient ordersClient;

    @Test
    public void contextLoads() {
        System.out.println(ordersClient.queryOrdersList());
    }
}
