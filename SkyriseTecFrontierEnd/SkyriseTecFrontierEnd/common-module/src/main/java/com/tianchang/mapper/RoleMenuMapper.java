package com.tianchang.mapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RoleMenuMapper {

    // 添加角色菜单关联
    public Integer insertRoleMenu( int roleId,List<Object> menuIds);

    // 删除角色菜单关联
    void deleteRoleMenu(int roleId);
}
