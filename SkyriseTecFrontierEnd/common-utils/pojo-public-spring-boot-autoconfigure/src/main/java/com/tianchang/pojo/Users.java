package com.tianchang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类，用于接收，封装，返回用户信息
 * */
//交给Spring IOC容器管理
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    //用户id
    private int userId;
    //部门id
    private int deptId;
    //用户名
    private String userName;
    //姓名
    private String nickName;
    //用户类型
    private short userType;
    //用户邮箱
    private String email;
    //手机号码
    private String phoneNumber;
    //身份证
    private String idCard;
    //性别,0男，1女
    private short gender;
    //用户头像
    private String image;
    //用户密码
    private String password;

    //账号状态（0正常 1停用）
    private short status;
    //删除状态(0,表示未删除，1表示已经删除)
    private short delFlag;
    //登陆时间
    private LocalDateTime loginTime;
    //创建时间
    private LocalDateTime createTime;
    //创建者
    private String createBy;
    //更新者
    private String updateBy;
    //更新时间
    private LocalDateTime updateTime;
    //备注
    private String remark;
}
