package com.tianchang.service.impl;

import com.tianchang.mapper.DeptMapper;
import com.tianchang.mapper.DeptRoleMapper;
import com.tianchang.pojo.Dept;
import com.tianchang.queryreceicepojo.QueryDept;
import com.tianchang.response.Result;
import com.tianchang.service.DeptService;
import com.tianchang.userServiceImplUtils.depts.DeptTreeHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    // 部门数据访问层
    @Resource
    private DeptMapper deptMapper;
    // 部门树结构辅助类
    @Resource
    private DeptTreeHelper deptTreeHelper;

    //角色部门关联依赖注入
    @Resource
    private DeptRoleMapper deptRoleMapper ;

    // 获取部门树结构
    @Override
    public Result getDeptTree() {

        try {
            List<Dept> deptList = deptMapper.getDeptTree();
//            log.info("调用构建之前查询结果：{}", deptList);
            // 使用辅助类构建树形结构
            List<Dept> tree = deptTreeHelper.buildDeptTree(deptList);

//            // 使用辅助类进行排序
//            deptTreeHelper.sortTree(tree);
        log.info("调用构建之后查询结果：{}", tree);
            return Result
                    .builder()
                    .code(200)
                    .msg("OK")
                    .data(tree)
                    .build();
        } catch (Exception e) {
            log.error("部门树查询异常", e);

        }
        return Result
                .builder()
                .code(500)
                .msg("系统繁忙，请稍后再试")
                .data(null)
                .build();
    }

    //获取部门数量
    @Override
    public Result getDeptCount() {

        try {
            int count =  deptMapper.getDeptCount();
            return Result
                    .builder()
                    .code(200)
                    .msg("OK")
                    .data(count)
                    .build();
        } catch (Exception e) {
            log.error("获取失败:",e);

        }
        return Result
                .builder()
                .code(500)
                .msg("系统繁忙，请稍后再试")
                .data(null)
                .build();
    }

    // 根据条件查询部门信息
    @Override
    public Result queryDeptListByCondition(QueryDept queryDept) {
        try {
            List<Dept> deptList = deptMapper.queryDeptListByCondition(queryDept);
            log.info("调用构建之前查询结果：{}", deptList);
            List<Dept> deptLists = deptTreeHelper.buildDeptTree(deptList);
//            log.info("查询结果：{}", deptLists);
            deptTreeHelper.sortTree(deptLists);
//            log.info("查询结果调用构建之后：{}", deptLists);
//            log.info("查询结果：{}", Result.success(deptLists));
            return Result
                    .builder()
                    .code(200)
                    .msg("OK")
                    .data(deptLists)
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
    //删除部门
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteDept(int deptId){
        if(deptId<=1009){
            log.error("不能删除当前部门");
          return   Result
                    .builder()
                    .code(500)
                    .msg("不能删除当前部门")
                    .data(null)
                    .build();
        }

        try {
            Integer effectCount  =  deptMapper.deleteDept(deptId);
            if(effectCount > 0){
                Integer deleteEffect = deptRoleMapper.deleteDeptRole(deptId);
                log.info("删除角色部门关联：{}",deleteEffect);
                if(deleteEffect > 0) {
                    return Result
                            .builder()
                            .code(200)
                            .msg("删除成功")
                            .data(null)
                            .build();
                }
            }
        } catch (Exception e) {
            log.error("删除失败了",e);
            // 重新抛出异常以触发事务回滚
//            throw new RuntimeException("删除部门时发生异常", e);
    }
        return Result
                .builder()
                .code(500)
                .msg("删除失败了")
                .data(null)
                .build();
    }

    //修改部门
    @Override
    public Result updateDept(Dept dept) {

        try {
            dept.setUpdateTime(LocalDateTime.now());
            System.out.println(dept);
            Integer effectCount =  deptMapper.updateDept(dept);
            if(effectCount > 0){
                return Result
                        .builder()
                        .code(200)
                        .msg("更新成功")
                        .data(null)
                        .build();
            }
        } catch (Exception e) {
            log.error("更新失败了",e);
        }
        return Result
                .builder()
                .code(500)
                .msg("更新失败了")
                .data(null)
                .build();
    }

    //添加部门
    @Override
    public Result addDept(Dept dept) {
        try {
            dept.setCreateTime(LocalDateTime.now());
            Integer effectCount = deptMapper.addDept(dept);
            if(effectCount > 0){
                return Result
                        .builder()
                        .code(200)
                        .msg("添加成功")
                        .build();
            }
        } catch (Exception e) {
            log.error("添加失败了",e);
        }

        return Result
                .builder()
                .code(500)
                .msg("添加失败了")
                .build();
    }
}