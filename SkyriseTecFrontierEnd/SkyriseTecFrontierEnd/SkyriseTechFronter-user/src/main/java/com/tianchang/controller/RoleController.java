package com.tianchang.controller;

import com.tianchang.pojo.Role;
import com.tianchang.queryreceicepojo.QueryRole;
import com.tianchang.response.Result;
import com.tianchang.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    //获取所有角色列表名称
    @GetMapping("/list")
    public Result queryRoleList() {

      Result result  =  roleService.getRoleList();

        return result;
    }

    //获取角色数量
    @GetMapping("/count")
    public Result getRoleCount() {

        Result result = roleService.getRoleCount();

        return result;
    }

    //添加角色
    @PostMapping("/add")
    public Result addRole(@RequestBody Role role){

        log.error("添加角色信息：{}",role);

      Result result  =   roleService.addRole(role);

        return result;
    }

    //修改角色信息
    @PutMapping("/update")
    public Result updateRole(@RequestBody Role role){

        log.info("要修改的数据为：{}",role);
      Result result  =  roleService.updateRole(role);

        return result;
    }

    //删除角色信息
    @DeleteMapping("/delete/{roleId}")
    public Result deleteRole(@PathVariable int roleId){

        log.info("要删除的数据为：{}",roleId);

      Result result  =  roleService.deleteRole(roleId);

        return result;
    }

    //跟据条件查询角色信息
    @PostMapping("/list")
    public Result queryRoleListByCondition(@RequestBody QueryRole queryRole){

        log.info("要查询的数据为：{}",queryRole);

      Result result  =  roleService.queryRoleListByCondition(queryRole);
        log.info("查询结果：{}",result);
        return result;
    }
}
