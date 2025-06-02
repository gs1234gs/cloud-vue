package com.tianchang.queryreceicepojo;

import com.tianchang.queryreceicepojo.queryinfo.PageInfos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 接收分页查询参数
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationQueryUser {
    //当前页码
    private Integer currentPage;
    //每页显示条数
    private Integer pageSize;
    //排序字段
    private String sortField;
    //排序方式(asc代表升序，desc代表降序)
    private String sortOrder;
    //查询条件
    private PageInfos pageInfo;

}
