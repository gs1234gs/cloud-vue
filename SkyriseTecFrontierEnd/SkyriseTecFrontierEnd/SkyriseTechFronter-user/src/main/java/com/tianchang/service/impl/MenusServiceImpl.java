package com.tianchang.service.impl;

import com.tianchang.mapper.MenusMapper;
import com.tianchang.pojo.Menus;
import com.tianchang.queryreceicepojo.QueryMenus;
import com.tianchang.response.Result;
import com.tianchang.service.MenusService;
import com.tianchang.userServiceImplUtils.menus.MenusTreeHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class MenusServiceImpl implements MenusService {

    @Resource
    private MenusMapper menusMapper;

    // 根据用户id查询菜单树
    @Override
    public Result getMenuTreeByUserId(Integer userId) {
        List<Menus> menus = null;
        try {
            menus = menusMapper.selectMenuTreeByUserId(userId);

            return Result
                    .builder()
                    .code(200)
                    .msg("查询成功")
                    .data(MenusTreeHelper.buildMenuTree(menus))
                    .build();
        } catch (Exception e) {
            log.info("查询菜单树失败");
        }
        return Result
                .builder()
                .code(500)
                .msg("系统繁忙，查询失败")
                .data(null)
                .build();
    }

    // 查询菜单列表
    @Override
    public Result getMenuList() {
        List<Menus> menus = menusMapper.selectMenuTreeByUserId(1);
//        if(true){
//            return Result.success(menus);
//        }
        menus = MenusTreeHelper.buildMenuTree(menus);
        return Result
                .builder()
                .code(200)
                .msg("查询成功")
                .data(menus)
                .build();
    }
    //添加菜单
    @Override
    public Result addMenu(Menus menu) {

        try {
            menu.setCreateTime(LocalDateTime.now());
            Integer countOffice  = menusMapper.addMenu(menu);
            if(countOffice > 0){
                return Result
                        .builder()
                        .code(200)
                        .msg("添加成功")
                        .data(null)
                        .build();
            }
        } catch (Exception e) {
            log.error("添加菜单失败",e);
//            throw new RuntimeException(e);
        }

        return Result
                .builder()
                .code(500)
                .msg("添加失败")
                .build();
    }

    //修改菜单
    @Override
    public Result updateMenu(Menus menu) {
        if(true){
            return Result
                    .builder()
                    .code(401)
                    .msg("目前该功能在测试阶段")
                    .build();
        }

        try {
            Integer countOffice =  menusMapper.updateMenu(menu);
            if(countOffice > 0){
                return Result
                        .builder()
                        .code(200)
                        .msg("修改成功")
                        .build();
            }
        } catch (Exception e) {
            log.error("修改菜单失败",e);
        }
        return Result
                .builder()
                .code(500)
                .msg("修改失败")
                .build();
    }

    //删除菜单
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteMenu(int menuId) {
        //注释内容，非必要使用功能
//        Integer countOffice =  menusMapper.deleteMenu(menuId);
//        menusMapper.deleteMenuByParentId(menuId);
        return Result
                .builder()
                .code(401)
                .msg("目前该功能在测试阶段")
                .build();
    }
    //条件查询菜单
    @Override
    public Result queryMenuListByCondition(QueryMenus queryMenu) {

        try {
            //调用dao层查询
            List<Menus> menus =  menusMapper.queryMenuListByCondition(queryMenu);
            if(!menus.isEmpty()){
                return Result
                                .builder()
                                .code(200)
                                .msg("查询成功")
                                .data(menus)
                                .build();
            }
        } catch (Exception e) {
            log.error("查询菜单失败",e);
        }

        return Result
                .builder()
                .code(401)
                .msg("系统繁忙，请稍后重试")
                .build();
    }

}