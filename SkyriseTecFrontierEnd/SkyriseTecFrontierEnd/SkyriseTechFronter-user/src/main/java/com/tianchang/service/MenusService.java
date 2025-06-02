package com.tianchang.service;

import com.tianchang.pojo.Menus;
import com.tianchang.queryreceicepojo.QueryMenus;
import com.tianchang.response.Result;

/**
 * 获取菜单接口
 * */
public interface MenusService {

    /**
     * 根据用户ID查询菜单树
     * @param userId 用户ID
     * @return 菜单树列表
     */
    Result getMenuTreeByUserId(Integer userId);


    // 获取菜单列表
    Result getMenuList();

    //添加菜单
    Result addMenu(Menus menu);

    //修改菜单
    Result updateMenu(Menus menu);
    //删除菜单
    Result deleteMenu(int menuId);
    //条件查询菜单
    Result queryMenuListByCondition(QueryMenus queryMenu);
}
