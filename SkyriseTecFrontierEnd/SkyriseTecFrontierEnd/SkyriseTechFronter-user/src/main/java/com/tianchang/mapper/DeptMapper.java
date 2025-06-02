package com.tianchang.mapper;

import com.tianchang.pojo.Dept;
import com.tianchang.queryreceicepojo.QueryDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 方案2：获取完整树形结构
    List<Dept> getDeptTree();

    //获取部门数量
    int getDeptCount();

    //根据条件查询部门列表
    List<Dept> queryDeptListByCondition(QueryDept queryDept);

    //删除部门
    Integer deleteDept(int deptId);


    //修改部门
    Integer updateDept(Dept dept);

    //添加部门
    Integer addDept(Dept dept);
}
