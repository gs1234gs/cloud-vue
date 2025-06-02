package com.tianchang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单类，用于封装菜单信息
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menus {

    // 主键id
    private int menuId;
    //菜单名称
    private String menuName;
    //父级菜单id
    private int parentId;
    //显示顺序
    private int orderNum;
    //路由地址
    private String path;
    //组件路径
    private String component;
    //路由参数
    private String query;
    //路由名称
    private String routeName;
    //是否外链,0是，，1否
    private short isFrame;
    //是否缓存，0是，1否
    private short isCache;
    //菜单类型，M目录，C菜单,F按钮
    private String menuType;
    //菜单状态，0显示，1隐藏
    private short visible;
    //菜单状态，0正常，1停用
    private short status;
    //权限标识
    private String perms;
    //图标
    private String icon;
    //创建者
    private String createBy;
    //创建时间
    private LocalDateTime createTime;
    //更新者
    private String updateBy;
    //更新时间
    private LocalDateTime updateTime;
    //备注
    private String remark;
    // 子菜单列表
    private List<Menus> children;
    //菜单层级
    private Integer level;
}
