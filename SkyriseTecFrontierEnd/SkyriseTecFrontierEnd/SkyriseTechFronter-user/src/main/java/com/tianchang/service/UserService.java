package com.tianchang.service;

import com.tianchang.pojo.Users;
import com.tianchang.queryreceicepojo.PaginationQueryUser;
import com.tianchang.response.Result;

import java.util.List;


/**
 * 用户接口，提供用户相关的服务，包括查询用户信息，查询用户列表等
 * */
public interface UserService {


    // 获取用户信息
    Result selectUserInfoBuId(int userId);

    // 根据条件查询用户列表
    Result queryUserListByCondition(PaginationQueryUser conditionQuery);

    // 获取用户列表总数
    Result queryUserListCount();

    // 添加用户
    Result addUser(Users user);

    // 修改用户
    Result updateUser(Users user);

    // 删除单个用户
    Result deleteUser(int userId);

    //跟据id获取权限
    List<String> selectUserPermsById(int userId);

    // 查询用户列表
    Result queryUserList();
}
