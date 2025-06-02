package com.tianchang.queryreceicepojo.queryinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfos {
    //查询条件,实体属性名
    private String userName;
    //查询条件,实体属性名
    private String nickName;
    //电话
    private String phoneNumber;
    //身份证
    private String idCard;
    //创建时间

    private LocalDateTime startTime;
    //修改时间

    private LocalDateTime endTime;
    //状态
    private Short status;
    //性别
    private Short gender;
    //部门
    private Integer deptId;

}
