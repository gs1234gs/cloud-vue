package com.tianchang.controller;

import com.tianchang.pojo.Dept;
import com.tianchang.queryreceicepojo.QueryDept;
import com.tianchang.response.Result;
import com.tianchang.service.DeptService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    // 获取部门树
    @GetMapping("/list")
    public Result getDeptTree()
    {
        Result deptTree = deptService.getDeptTree();
        log.info("获取部门树成功{}", deptTree);
        return deptTree;
    }

    //条件查询部门
    @PostMapping("/list")
    public Result queryDeptListByCondition(@RequestBody QueryDept queryDept)
    {
        Result result = deptService.queryDeptListByCondition(queryDept);
        log.info("条件查询部门成功,{}", result);
        return result;
    }

    //增加部门
    @PostMapping("/add")
    public Result addDept(Dept dept) {
     Result result = deptService.addDept(dept);

        return result;
    }


    //删除部门
    @DeleteMapping("/delete/{deptId}")
    public Result deleteDept(@PathVariable int deptId) {

       Result result  =  deptService.deleteDept(deptId);

        return result;
    }

    //修改部门
    @PutMapping("/update")
    public Result updateDept(@RequestBody Dept dept) {
       Result result = deptService.updateDept(dept);
        return result;
    }


    //查询部门人员
    public Result getDeptUser(int deptId)
    {
        return null;
    }

    //获取部门总数
    @GetMapping("/count")
    public Result getDeptCount()
    {
     Result result =   deptService.getDeptCount();
        return result;
    }


}
