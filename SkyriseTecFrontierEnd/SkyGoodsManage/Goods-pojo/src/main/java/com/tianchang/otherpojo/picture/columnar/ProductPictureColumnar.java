package com.tianchang.otherpojo.picture.columnar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductPictureColumnar {
        // 商品类型集合
    private YAxis yAxis;
    // 商品销售数量集合
    private List<Series> series;

}
