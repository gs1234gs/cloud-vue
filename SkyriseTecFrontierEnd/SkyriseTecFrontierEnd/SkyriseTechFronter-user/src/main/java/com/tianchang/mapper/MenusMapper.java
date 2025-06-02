package com.tianchang.mapper;

import com.tianchang.pojo.Menus;
import com.tianchang.queryreceicepojo.QueryMenus;
import com.tianchang.response.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenusMapper {
    /**
     * 根据用户ID查询用户拥有的菜单权限（包含层级结构）
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<Menus> selectMenuTreeByUserId( Integer userId);


    // 添加菜单
    Integer addMenu(Menus menu);

    // 修改菜单
    Integer updateMenu(Menus menu);

//    // 删除菜单
//    Integer deleteMenu(int menuId);
//
//    //跟据父级菜单删除子级菜单
//    Integer deleteMenuByParentId(int parentId);

    // 根据条件查询菜单列表
    List<Menus> queryMenuListByCondition(QueryMenus queryMenu);

    /**
     * 根据父菜单ID查询子菜单列表
     * @param parentId 父菜单ID
     * @return 子菜单列表
     */
//    List<Menus> selectMenuListByParentId(@Param("parentId") Integer parentId);
}
