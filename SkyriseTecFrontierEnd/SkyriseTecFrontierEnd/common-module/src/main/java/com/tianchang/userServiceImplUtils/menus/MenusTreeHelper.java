package com.tianchang.userServiceImplUtils.menus;


import com.tianchang.pojo.Menus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Component
public class MenusTreeHelper {

    /**
     * 递归获取子菜单
     * @param parentId 父菜单ID
     * @param menus 所有菜单列表
     * @return 子菜单列表
     */
    public static List<Menus> getChildMenus(Integer parentId, List<Menus> menus) {
        List<Menus> childList = new ArrayList<>();

        for (Menus menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(getChildMenus(menu.getMenuId(), menus));
                childList.add(menu);
            }
        }

        // 按orderNum排序
        childList.sort((m1, m2) -> m1.getOrderNum() - m2.getOrderNum());

        return childList.isEmpty() ? null : childList;
    }

    /**
     * 构建前端所需的菜单树结构
     * @param menus 菜单列表
     * @return 树形结构的菜单列表
     */
    // 构建菜单树
    public static List<Menus> buildMenuTree(List<Menus> menus) {
        try {
            List<Menus> result = new ArrayList<>();

            // 获取所有顶级菜单
            List<Menus> topMenus = menus.stream()
                    .filter(menu -> menu.getParentId() == 0)
                    .sorted((m1, m2) -> m1.getOrderNum() - m2.getOrderNum())
                    .collect(Collectors.toList());

            for (Menus menu : topMenus) {
                // 使用工具类获取子菜单
                menu.setChildren(getChildMenus(menu.getMenuId(), menus));
                result.add(menu);
            }

            return result;
        } catch (Exception e) {
            log.error("构建菜单树失败", e);
            return null;
        }
    }
}