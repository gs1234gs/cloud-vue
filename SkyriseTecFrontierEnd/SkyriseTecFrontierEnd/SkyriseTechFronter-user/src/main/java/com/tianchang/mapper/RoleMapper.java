package com.tianchang.mapper;

import com.tianchang.pojo.Role;
import com.tianchang.queryreceicepojo.QueryRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 角色数据访问层接口
 * */
@Mapper
public interface RoleMapper {

    // 获取角色名称列表
    List<Role> getRoleList();

    // 添加角色
    Integer addRole(Role role);

    // 更新角色
    Integer updateRole(Role role);

    // 删除角色
    Integer deleteRole(int roleId);

    // 获取角色ID
    Integer getRoleId(String roleName);

    // 根据条件查询角色列表
    List<Role> queryRoleListByCondition(QueryRole queryRole);

    // 获取角色总数
    Integer getCount();
}
