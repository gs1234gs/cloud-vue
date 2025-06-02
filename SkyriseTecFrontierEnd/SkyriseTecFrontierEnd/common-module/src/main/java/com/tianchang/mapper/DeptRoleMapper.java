package com.tianchang.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptRoleMapper {
    //删除角色部门关联表数据
    @Delete("delete from stf.sys_dept where dept_id = #{deptId}")
    Integer deleteDeptRole(@Param("deptId") int deptId);
}
