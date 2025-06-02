package com.tianchang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    // 总记录数
    private long   pageTotal;
    // 总数据列表
    private List<?> pageRows;
}
