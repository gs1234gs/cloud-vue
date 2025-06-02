package com.tianchang;


import com.tianchang.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class SkyOrdersAppApplicationTests {
    @Resource
    private OrderMapper orderMapper;
    @Test
    void contextLoads() {
//        System.out.println(orderMapper.orderCount(1));
    }

}
