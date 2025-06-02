package com.tianchang.service.impl;

import com.tianchang.isEmptyReceive.IsEmptyReceive;
import com.tianchang.mapper.LoginMapper;
import com.tianchang.pojo.Users;
import com.tianchang.service.LoginService;
import com.tianchang.service.UserService;
import com.tianchang.usersdetails.UserDetailsReWrite;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 登录接口服务实现类，用于处理登录请求
 * */
@Slf4j
@Service
@RequiredArgsConstructor // Lombok生成构造函数
public class LoginServiceImpl implements LoginService {

    //使用注入的方式获取LoginMapper接口的实现类
    @Resource
    private LoginMapper loginMapper;

    @Resource
    private UserService userService;
    @Override
    public UserDetailsReWrite loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        Users user = findUserByUsername(username);

        // 构建 UserDetailsReWrite 对象
        return buildUserDetails(user);
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 查询到的用户信息
     * @throws UsernameNotFoundException 如果用户不存在
     */
    private Users findUserByUsername(String username) throws UsernameNotFoundException {
        Users user = loginMapper.findByUsername(username);
        if (IsEmptyReceive.isEmpty(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    /**
     * 构建 UserDetailsReWrite 对象
     * @param user 用户信息
     * @return UserDetailsReWrite 对象
     */
    private UserDetailsReWrite buildUserDetails(Users user) {
        // 获取用户的角色和权限
        List<String> perms = userService.selectUserPermsById(user.getUserId());

        // 将用户角色转换为 GrantedAuthority
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 添加用户角色
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType()));
        // 添加用户权限
        for (String perm : perms) {
            // 如果需要，可以在这里添加前缀，比如 "ROLE_"
            authorities.add(new SimpleGrantedAuthority(perm));
        }
        // 构建 UserDetailsReWrite 对象
        return UserDetailsReWrite.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(authorities)
                .users(user)
                .build();
    }

}
