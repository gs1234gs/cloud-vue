package com.tianchang.otherpojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCount {
    //商品类型
    private String productType;
    //商品销量
    private Integer productAmount;
    //商品周同比
    private String productWeek;
    //时间
    private LocalDateTime time;
}
