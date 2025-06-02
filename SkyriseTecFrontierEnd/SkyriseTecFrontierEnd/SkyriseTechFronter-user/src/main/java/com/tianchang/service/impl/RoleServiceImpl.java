package com.tianchang.service.impl;

import com.tianchang.mapper.RoleMapper;
import com.tianchang.mapper.RoleMenuMapper;
import com.tianchang.pojo.Role;
import com.tianchang.queryreceicepojo.QueryRole;
import com.tianchang.response.Result;
import com.tianchang.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色服务实现类
 * */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleMenuMapper roleMenuMapper;
    //获取角色列表
    @Override
    public Result getRoleList() {
        try {
            List<Role> roleNameList = roleMapper.getRoleList();

            return Result
                    .builder()
                    .code(200)
                    .msg("获取角色列表成功")
                    .data(roleNameList)
                    .build();
        } catch (Exception e) {
            log.info("获取角色列表失败", e);
        }
        return Result
                .builder()
                .code(500)
                .msg("获取角色列表失败")
                .data(null)
                .build();
    }

    //添加角色
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addRole(Role role) {

        try {
            role.setCreateTime(LocalDateTime.now());
            Integer effectCount =  roleMapper.addRole(role);
            int roleId = roleMapper.getRoleId(role.getRoleName());
            if(effectCount >0){
                roleMenuMapper.insertRoleMenu(roleId,role.getMenuListId());
                return Result
                        .builder()
                        .code(200)
                        .msg("添加角色成功")
                        .data(effectCount)
                        .build();
            }
        } catch (Exception e) {
            log.error("添加角色失败", e);
            throw new RuntimeException("添加角色失败");
        }
        return Result
                .builder()
                .code(500)
                .msg("添加角色失败")
                .data(null)
                .build();
    }


    //更新角色
    @Override
    public Result updateRole(Role role) {

        try {
            role.setUpdateTime(LocalDateTime.now());
            Integer effectCount = roleMapper.updateRole(role);
            if(effectCount >0){
                return Result
                        .builder()
                        .code(200)
                        .msg("更新角色成功")
                        .data(effectCount)
                        .build();
            }
        } catch (Exception e) {
            log.error("更新角色失败", e);
        }

        return Result
                .builder()
                .code(500)
                .msg("更新角色失败")
                .data(null)
                .build();
    }

    //删除角色
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteRole(int roleId) {
        try {
            Integer effectCount = roleMapper.deleteRole(roleId);
            roleMenuMapper.deleteRoleMenu(roleId);
            if(effectCount >0){
                return Result
                        .builder()
                        .code(200)
                        .msg("删除角色成功")
                        .data(effectCount)
                        .build();
            }
        } catch (Exception e) {
            log.error("删除角色失败", e);
        }
        return Result
                .builder()
                .code(500)
                .msg("删除角色失败")
                .data(null)
                .build();
    }

    //  根据条件查询角色
    @Override
    public Result queryRoleListByCondition(QueryRole queryRole) {

        try {
            List<Role> roleList =  roleMapper.queryRoleListByCondition(queryRole);

            log.info("查询结果：{}",roleList);

            return Result
                    .builder()
                    .code(200)
                    .msg("查询成功")
                    .data(roleList)
                    .build();
        } catch (Exception e) {
            log.error("查询失败:",e);
        }
        return Result
                .builder()
                .code(500)
                .msg("系统繁忙，请稍后再试")
                .data(null)
                .build();
    }

    // 获取角色数量
    @Override
    public Result getRoleCount() {
       Integer countRole = roleMapper.getCount();
        return Result
                .builder()
                .code(200)
                .msg("ok")
                .data(countRole)
                .build();
    }
}
