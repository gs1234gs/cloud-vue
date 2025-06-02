package com.tianchang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianchang.isEmptyReceive.IsEmptyReceive;
import com.tianchang.mapper.UserMapper;
import com.tianchang.mapper.UserRoleMapper;
import com.tianchang.pojo.PageBean;
import com.tianchang.pojo.Users;
import com.tianchang.queryreceicepojo.PaginationQueryUser;
import com.tianchang.service.UserService;
import com.tianchang.userServiceImplUtils.user.UserHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.tianchang.response.Result;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户服务实现类，实现接口内的方法
 * */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    // 注入mapper
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserHelper userHelper;

    @Resource
    private UserRoleMapper  userRoleMapper;

    // 查询单个用户信息
    @Override
    public Result selectUserInfoBuId(int userId) {

        // 查询用户信息和菜单信息
        Users user = null;
        try {
            user = userMapper.selectUserInfoBuId(userId);
//            log.info("查询用户信息：{}",user);
            if(user != null){
                return Result
                        .builder()
                        .code(200)
                        .msg("查询用户信息成功")
                        .data(user)
                        .build();
            }
        } catch (Exception e) {
            log.error("查询用户信息失败", e);
        }

        return Result
                .builder()
                .code(500)
                .msg("查询用户信息失败")
                .data(null)
                .build();
    }

    // 根据条件查询用户列表
    @Override
    public Result queryUserListByCondition(PaginationQueryUser conditionQuery) {
        try {
            List<Users> users;
            //判断条件是否为空
            if(IsEmptyReceive.isEmpty(conditionQuery.getPageInfo())){
                System.out.println("条件为空，执行分页");
                if(conditionQuery.getCurrentPage() == null || conditionQuery.getPageSize() == null){
                    log.info("所有条件为空,默认前10");
                    PageHelper.startPage(1, 10);
                    // 执行查询
                    users = userMapper.queryUserList();
                    // 使用 PageInfo 获取分页信息（PageHelper 提供的辅助类）
                    PageInfo<Users> pageInfo = new PageInfo<>(users);
                    // 封装分页信息到 PageBean
                    PageBean pageBean = new PageBean(pageInfo.getTotal(), pageInfo.getList());
                    // 打印分页信息
//                log.info("查询用户列表成功，结果：{}", users);
                    // 返回成功结果
                    return Result
                            .builder()
                            .code(200)
                            .msg("查询用户列表成功")
                            .data(pageBean)
                            .build();
                }
                log.info("分页条件不为空,执行分页操作");
                PageHelper.startPage(conditionQuery.getCurrentPage(), conditionQuery.getPageSize());
                // 执行查询
                users = userMapper.queryUserList();
                // 使用 PageInfo 获取分页信息（PageHelper 提供的辅助类）
                PageInfo<Users> pageInfo = new PageInfo<>(users);
                // 封装分页信息到 PageBean
                PageBean pageBean = new PageBean(pageInfo.getTotal(), pageInfo.getList());
                // 打印分页信息
//                log.info("查询用户列表成功，结果：{}", users);
                // 返回成功结果
                return Result
                        .builder()
                        .code(200)
                        .msg("查询用户列表成功")
                        .data(pageBean)
                        .build();
            }
            log.info("条件不为空,执行匹配条件操作");
          List<Users> usersList =  userMapper.queryUserListByCondition(conditionQuery.getPageInfo());
            Integer userListCount = userMapper.queryUserListCount();
            PageBean pageBean = new PageBean(userListCount, usersList);
//            log.info("查询用户列表成功，结果：{}", usersList);
          return Result
                  .builder()
                  .code(200)
                  .msg("查询用户列表成功")
                  .data(pageBean)
                  .build();
        }
        catch (Exception e) {
            // 记录错误日志
            log.error("查询用户列表失败", e);
        }
        // 返回错误结果
        return Result
                .builder()
                .code(500)
                .msg("查询用户列表失败")
                .data(null)
                .build();
    }

    // 查询用户列表总数
    @Override
    public Result queryUserListCount() {

        try {
            Integer count = userMapper.queryUserListCount();
            if(count == null){
                return Result
                        .builder()
                        .code(500)
                        .msg("查询用户列表总数失败")
                        .data(null)
                        .build();
            }
            return Result
                    .builder()
                    .code(200)
                    .msg("查询用户列表总数成功")
                    .data(count)
                    .build();
        } catch (Exception e){
        log.error("查询用户列表总数失败", e);
        }
        return Result
                .builder()
                .code(500)
                .msg("查询用户列表总数失败")
                .data(null)
                .build();
    }

    // 添加用户
    @Transactional
    @Override
    public Result addUser(Users user) {

        try {
            user.setCreateTime(LocalDateTime.now());
            Integer addCount = userMapper.addUser(user);
            if(addCount == 0){
                return Result
                        .builder()
                        .code(500)
                        .msg("用户已经存在,添加用户失败")
                        .data(null)
                        .build();
            }
            // 获取用户id
           int userId = userMapper.getUserId(user.getUserName());
            Integer countAdd = userRoleMapper.insertUserRole(userId, user.getUserType() + 1);
            return Result
                    .builder()
                    .code(200)
                    .msg("添加用户成功")
                    .data(countAdd)
                    .build();
        } catch (DuplicateKeyException e) { // 假设用户已存在会抛出这个异常（根据具体情况调整）
            log.error("添加用户失败，用户已存在", e);
            return Result
                    .builder()
                    .code(400)
                    .msg("添加用户失败，用户已存在")
                    .data(null)
                    .build();
        } catch (Exception e) {
            log.error("添加用户失败，发生未知异常", e);
        }
        return Result
                .builder()
                .code(500)
                .msg("添加用户失败,发生未知异常")
                .data(null)
                .build();
    }

    // 更新用户
    @Transactional
    @Override
    public Result updateUser(Users user) {

        try {
            user.setUpdateTime(LocalDateTime.now());
            Integer updateCount = userMapper.updateUser(user);
            if(updateCount == 0){
                return Result
                        .builder()
                        .code(500)
                        .msg("更新用户失败,用户不存在")
                        .data(null)
                        .build();
            }
            userRoleMapper.updateUserRole(user.getUserId(),user.getUserType() + 1);
            return Result
                    .builder()
                    .code(200)
                    .msg("更新用户成功")
                    .data(updateCount)
                    .build();
        } catch (Exception e) {
            log.error("更新用户失败", e);
        }
        return Result
                .builder()
                .code(500)
                .msg("更新用户失败")
                .data(null)
                .build();
    }

    // 删除单个用户
    @Transactional
    @Override
    public Result deleteUser(int userId) {

        if(userId <= 1019){
            return userHelper.isDelete(userId);
        }
        try {
            Integer deleteCount = userMapper.deleteUser(userId);
            if(deleteCount == 0){
                return Result
                        .builder()
                        .code(500)
                        .msg("删除失败,用户不存在")
                        .data(null)
                        .build();
            }
            userRoleMapper.deleteUserRoleByUserId(userId);
            return Result
                    .builder()
                    .code(200)
                    .msg("删除成功")
                    .data(deleteCount)
                    .build();
        } catch (Exception e) {
            log.error("删除用户失败", e);
    }
        return Result
                .builder()
                .code(500)
                .msg("删除失败，服务器发生异常")
                .data(null)
                .build();
    }
    //跟据id限获取角色权限
    @Override
    public List<String> selectUserPermsById(int userId) {
        try {
            List<String> permsList = userMapper.selectUserPermsById(userId);
            return permsList;
        } catch (Exception e) {
            log.error("获取用户权限失败", e);
        }
        return null;
    }

    @Override
    public Result queryUserList() {
        try {
            PageHelper.startPage(1, 10);
            // 执行查询
           List<Users> users = userMapper.queryUserList();
            // 使用 PageInfo 获取分页信息（PageHelper 提供的辅助类）
            PageInfo<Users> pageInfo = new PageInfo<>(users);
            // 封装分页信息到 PageBean
            PageBean pageBean = new PageBean(pageInfo.getTotal(), pageInfo.getList());
            return Result
                    .builder()
                    .code(200)
                    .msg("查询用户列表成功")
                    .data(pageBean)
                    .build();
        } catch (Exception e) {
            log.error("查询用户列表失败", e);
        }
        return Result
                .builder()
                .code(500)
                .msg("查询用户列表失败")
                .build();
    }
}
