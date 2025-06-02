package com.tianchang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRoleMapper {
    // 插入用户角色关联表
    @Insert("insert into stf.sys_user_role(user_id,role_id) values(#{userId},#{roleId})")
    public Integer insertUserRole(@Param("userId")int userId,@Param("roleId") int roleId);
    // 根据用户id删除用户角色关联表
    @Update("delete from stf.sys_user_role where user_id=#{userId}")
    public void deleteUserRoleByUserId(@Param("userId") int userId);

    //修改用户角色表
    @Update("update stf.sys_user_role set role_id=#{roleId} where user_id=#{userId}")
    public void updateUserRole(@Param("userId")int userId, @Param("roleId") int roleId);
}
