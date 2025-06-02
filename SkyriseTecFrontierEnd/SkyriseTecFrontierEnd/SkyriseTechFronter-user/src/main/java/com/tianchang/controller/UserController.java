package com.tianchang.controller;

import com.tianchang.pojo.Users;
import com.tianchang.queryreceicepojo.PaginationQueryUser;
import com.tianchang.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.tianchang.response.Result;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    // 查询单个用户信息，用于用户登录时获取
    @GetMapping("/list/{userId}")
    public Result queryUserInfo(@PathVariable int userId){

        // 调用UserService接口的getUserInfo方法，获取用户信息
       Result result = userService.selectUserInfoBuId(userId);

       log.info("查询用户信息：{}",result);

        return result;
    }

    //获取用户总的条数，这个接口后续可能会用到，暂时未用到
    @GetMapping("/count")
    public Result queryUserListCount(){
        Result result = userService.queryUserListCount();
//        log.info("总共有{}条数据",result.getData());
        return result;
    }

    //新增用户
    @PostMapping("/add")
    public Result addUser(@RequestBody Users user){
//        log.info("新增用户信息：{}",user);
        Result result = userService.addUser(user);
//        log.info("新增用户信息：{}",result);
        return result;
    }

    //修改用户
    @PutMapping("/update")
    public Result updateUser(@RequestBody Users user){
        log.info("修改用户信息：{}",user);
        Result result = userService.updateUser(user);
        log.info("修改用户信息：{}",result);
        return result;
    }

    //删除用户
    @DeleteMapping("/delete/{userId}")
    public Result deleteUser(@PathVariable int userId){
        log.info("删除用户信息：{}",userId);
        Result result = userService.deleteUser(userId);
        log.info("删除用户信息：{}",result);
        return result;
    }

    @PostMapping("/list")
    public Result queryUserListByConditions(@RequestBody PaginationQueryUser conditionQuery){
        log.info("查询用户列表信息：{}",conditionQuery);
        Result result = userService.queryUserListByCondition(conditionQuery);
        log.info("查询用户列表信息：{}",result);
        return result;
    }
    //获取户列表名称
    @GetMapping("/list")
    public Result queryUserList(){
        Result result = userService.queryUserList();
        return result;
    }
}
