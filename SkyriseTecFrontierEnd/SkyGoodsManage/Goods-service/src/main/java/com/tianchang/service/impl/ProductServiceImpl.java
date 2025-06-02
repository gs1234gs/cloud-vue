package com.tianchang.service.impl;

import com.tianchang.configOpenfeign.client.OrdersClient;
import com.tianchang.otherpojo.ProductCount;
import com.tianchang.otherpojo.picture.columnar.ProductPictureColumnar;
import com.tianchang.otherpojo.picture.columnar.Series;
import com.tianchang.otherpojo.picture.columnar.YAxis;
import com.tianchang.pojo.Orders;
import com.tianchang.pojo.Product;
import com.tianchang.mapper.ProductMapper;
import com.tianchang.response.Result;
import com.tianchang.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;
    @Resource
    OrdersClient ordersClient;

    //查询商品列表
    @Override
    public List<Product> queryProductList() {
        try{
            List<Product> productList = productMapper.queryProductList();
            return productList;
        }
        catch (Exception e){
            log.error("查询商品列表失败");
        }

        return null;
    }

    //商品统计
    @Override
    public Result queryProductAmount() {
        ArrayList<ProductCount> productCountList = new ArrayList<>();
        //获取商品列表
        List<Product> productList = productMapper.queryProductList();
        //获取订单列表
        List<Orders> ordersList = ordersClient.queryOrdersList();
        System.out.println("订单列表:"+ ordersList);
        if(ordersList.isEmpty()){
            return Result.error();
        }
        //遍历商品列表

        int count = 0;
        for (int i = 0; i < productList.size(); i++) {
            Integer productId1 = productList.get(i).getProductId();
            //遍历订单列表
            Integer countWeek = 0;

            for (int j = 0; j < ordersList.size(); j++) {
                Integer productId2 = ordersList.get(j).getProductId();
                //判断商品id是否相等
                if( Objects.equals(productId1, productId2)){
            count += ordersList.get(j).getProductCount();
            countWeek  += ordersList.get(j).getProductCount();

                }
            }
            //计算商品周销售量比，此处仅仅为演示过程，并非正确计算
            BigDecimal productWeek = BigDecimal.valueOf(
                    countWeek*1.00 / (
                            countWeek+productList.get(i).getProductStock()*1.00
                            + countWeek*1.00
                            ) * 100
            ).setScale(2, RoundingMode.HALF_UP);
            productCountList.add(
                    ProductCount.builder()
                            .productType(productList.get(i).getProductName())
                            .productAmount(countWeek)
                            .productWeek(productWeek.toString()+"%")
                            .time(LocalDateTime.now())
                            .build()
            );

        }
        ArrayList<Object> listCount = new ArrayList<>();
        listCount.add(count);
        listCount.add(productCountList);
        return Result.builder().code(200)
                .msg("查询成功")
                .data(listCount)
                .build();
    }

    @Override
    public Result queryProductSeries() {
      List<String> list = List.of("Brazil", "Indonesia", "USA", "India", "China", "World");
        List<Series> list1 = new ArrayList<>();
        ProductPictureColumnar
                .builder()
                .yAxis(YAxis
                        .builder()
                        .type("category")
                        .data(list)
                        .build())
                .series(
                     list1
                );
        return null;
    }
}
