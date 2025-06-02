package com.tianchang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    // 订单ID
    private Integer ordersId;
    //商品id
    private Integer productId;
    //商品数量
    private Integer productCount;
    //实际付款
    private Double actualCost;
    //订单编号
    private String ordersNumber;
    //支付方式
    private String costMethod;
    //发票类型
    private String invoiceType;
    //支付时间
    private LocalDateTime costTime;
    //下单时间
    private LocalDateTime placeOrderTime;
    //配送方式
    private String deliveryMethod;
    //收货人id
    private Integer customerId;
    //收货地址
    private String receiptAddress;
    //配送时间
    private LocalDateTime deliveryTime;
    //收获方式
    private String receiptMethod;

}
