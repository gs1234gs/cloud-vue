package com.tianchang.mapper;

import com.tianchang.pojo.LoginUser;
import com.tianchang.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * mapper接口,用于实现登录功能，与数据库的交互，自动映射xml文件
 * */
@Mapper
public interface LoginMapper {

    //更新用户登录信息
    void updateUserLoginInfo(@Param("userId") int userId ,@Param("lastLoginTime") LocalDateTime lastLoginTime);
     //根据用户名查询用户信息
    Users findByUsername(String username);
}
