package com.tianchang.controller;

import com.tianchang.pojo.Product;
import com.tianchang.response.Result;
import com.tianchang.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequestMapping("/product")
@RestController
public class ProductController {
    @Resource
    ProductService productService;
    //查询商品信息
//    @GetMapping("/list")
//    public List<Product> productList(@RequestHeader(value = "token",required = false) String token){
//
//        Result result =  productService.queryProductList();
//        System.out.println("请求头 ： "+token);
//        return (List<Product>)result.getData();
//    }
    //获取商品列表
    @GetMapping("/list")
    public Result productList(){

        List<Product> list =  productService.queryProductList();
        log.info("查询商品列表成功{}", list);
        return Result
                .builder()
                .code(200)
                .msg("查询成功")
                .data(list)
                .build();
    }
    //商品统计
    @GetMapping("amount")
    public Result productAmount(){

        Result result =  productService.queryProductAmount();
//        log.error("商品统计成功{}", result);
        return result;
    }
    @GetMapping("series")
    public Result productSeries(){

        Result result =  productService.queryProductSeries();
        return result;
    }

}
