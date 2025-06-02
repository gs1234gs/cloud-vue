package com.tianchang.configOpenfeign.client;

import com.tianchang.pojo.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient("sky-orders-APP")
public interface OrdersClient {
    @GetMapping("/orders/list")
    public List<Orders> queryOrdersList();
}
