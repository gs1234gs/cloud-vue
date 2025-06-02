package com.tianchang.mapper;

import com.tianchang.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {


    List<Orders> orderList();
}
