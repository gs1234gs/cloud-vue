package com.tianchang.mapper;

import com.tianchang.pojo.Users;
import com.tianchang.queryreceicepojo.queryinfo.PageInfos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户dao层，继承了MyBatis的Mapper接口，用于操作数据库
 * */
@Mapper
public interface UserMapper {

    // 查询用户信息和菜单信息
    Users selectUserInfoBuId(@Param("userId") int userId);

    // 查询用户列表
    List<Users> queryUserList();

    // 根据条件查询用户列表
    List<Users> queryUserListByCondition(PageInfos pageInfo);

    // 根据条件查询用户列表总数
    Integer queryUserListCount();

    // 添加用户
    Integer addUser(Users user);

    // 删除用户
    Integer deleteUser(Integer userId);

    // 修改用户
    Integer updateUser(Users user);

    // 根据用户名查询用户id
    int getUserId(String userName);

    // 根据用户id查询菜单权限
    List<String> selectUserPermsById(Integer userId);
}
