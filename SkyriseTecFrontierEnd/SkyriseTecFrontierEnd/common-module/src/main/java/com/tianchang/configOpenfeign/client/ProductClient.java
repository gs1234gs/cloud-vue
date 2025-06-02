package com.tianchang.configOpenfeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient("GoodsApp")
public interface ProductClient {
    @GetMapping("/product/{ur}")
    public List<Object> queryProductList(@PathVariable("ur") String ur);
}
