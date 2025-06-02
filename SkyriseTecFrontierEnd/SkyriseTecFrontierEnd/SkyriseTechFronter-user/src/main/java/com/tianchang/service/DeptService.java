package com.tianchang.service;

import com.tianchang.pojo.Dept;
import com.tianchang.queryreceicepojo.QueryDept;
import com.tianchang.response.Result;


public interface DeptService {
    // 获取部门树数据
   Result getDeptTree();

   //获取部门总数
    Result getDeptCount();

    //根据条件查询部门
    Result queryDeptListByCondition(QueryDept queryDept);

    //删除部门
    Result deleteDept(int deptId);

    //修改部门
    Result updateDept(Dept dept);

    //添加部门
    Result addDept(Dept dept);
}
