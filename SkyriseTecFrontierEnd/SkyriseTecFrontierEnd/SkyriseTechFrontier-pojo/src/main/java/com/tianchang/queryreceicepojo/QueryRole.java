package com.tianchang.queryreceicepojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryRole {
    //角色名称
    private String roleName;
    //角色标识
    private String roleKey;
    //角色描述
    private String remark;
    //角色状态
    private Integer status;
    //角色创建开始时间
    private LocalDateTime startTime;
    //结束时间
    private LocalDateTime endTime;
}
