package com.tianchang.service.impl;

import com.tianchang.pojo.Orders;
import com.tianchang.mapper.OrderMapper;
import com.tianchang.response.Result;
import com.tianchang.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Orders> orderList() {
        List<Orders> orderList = null;
        try {
            orderList = orderMapper.orderList();
        } catch (Exception e) {
            log.error("查询订单列表失败",e);
            return null;
        }
        return orderList;
    }
}
