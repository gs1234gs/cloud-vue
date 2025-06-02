package com.tianchang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    // 商品id
    private Integer productId;
    // 商品名称
    private String productName;
    // 商品价格
    private Double productPrice;
    //商品成本
    private Double productCost;
    //商品库存
    private Integer productStock;
    //商品状态
    private Integer status;
    //商品图片
    private String image;
    //商品描述
    private String description;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //品牌所属国家
    private String brandCountry;

}
