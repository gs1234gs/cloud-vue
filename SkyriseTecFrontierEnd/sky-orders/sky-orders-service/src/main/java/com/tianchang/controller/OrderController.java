package com.tianchang.controller;

import com.tianchang.pojo.Orders;
import com.tianchang.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    //获取订单数量
    @GetMapping("/list")
    public List<Orders> orderCount(){
        List<Orders> ordersList = orderService.orderList();
        return ordersList;
    }
}
