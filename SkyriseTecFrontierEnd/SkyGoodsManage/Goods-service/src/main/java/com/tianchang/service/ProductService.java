package com.tianchang.service;


import com.tianchang.pojo.Product;
import com.tianchang.response.Result;

import java.util.List;

public interface ProductService {

    //查询商品列表
    List<Product> queryProductList();

    //查询商品统计
    Result queryProductAmount();

    //查询商品系列
    Result queryProductSeries();
}
