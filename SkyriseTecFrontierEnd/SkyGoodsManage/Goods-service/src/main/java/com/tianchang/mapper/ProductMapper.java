package com.tianchang.mapper;

import com.tianchang.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //查询商品列表
    List<Product> queryProductList();

}
