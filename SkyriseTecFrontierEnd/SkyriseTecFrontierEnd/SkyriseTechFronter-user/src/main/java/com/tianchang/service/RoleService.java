package com.tianchang.service;

import com.tianchang.pojo.Role;
import com.tianchang.queryreceicepojo.QueryRole;
import com.tianchang.response.Result;
/**
 * 角色业务层接口
 * */
public interface RoleService {

    //查询所有角色名称
    Result getRoleList();

    //添加角色
    Result addRole(Role role);

    //修改角色
    Result updateRole(Role role);

    //删除角色
    Result deleteRole(int roleId);

    //根据条件，查询角色
    Result queryRoleListByCondition(QueryRole queryRole);

    //查询角色数量
    Result getRoleCount();
}
