package com.tianchang;

import com.tianchang.mapper.UserMapper;

import com.tianchang.pojo.Dept;
import com.tianchang.pojo.Users;
import com.tianchang.response.Result;
import com.tianchang.service.*;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class AppApplicationTests {

    @Resource
    private LoginService loginService;
    @Resource
    private UserMapper userMapper;

    @Resource
    private MenusService menusService;
    @Resource
    private UserService userService;
//    @Test
//    void contextLoads() {
//
////        System.out.println(userMapper.selectUserWithMenus(1));
//
//    }

//对菜单树进行测试
    @Test
    public void getMenuTree() {
        Result menuTreeByUserId = menusService.getMenuTreeByUserId(1);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(menuTreeByUserId.getData());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(userMapper.selectUserInfoBuId(1));
    }
    @Test
    public void queryUserList() {
    }

//
//    @Test
//    public void selects(){
//        Integer userId = 0;
//        String nickName = "刘";
//        ConditionsQuery conditions = new ConditionsQuery();
//        conditions.setNickName(nickName);
//        System.out.println(IsEmptyReceive.isEmpty(conditions));
//
//        System.out.println();
//
//
//        Result result = userService.queryUserListByCondition(conditions);
//        System.out.println(result);
//        Result result1 = userService.queryUserListCount();
//        System.out.println("------------------------"+result1);
//    }
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Test
//    public void redisTest(){
//
//    //String 类型
////        redisTemplate.opsForValue().set("nickName","天畅");
////        System.out.println(redisTemplate.opsForValue().get("nickName"));
//
//    }
//
    @Test
    public void insert(){
        Users users = new Users();
        users.setDeptId(1);
        users.setUserName("tianchang1");
        users.setNickName("天畅");
        users.setPassword("123456");
        users.setUserType((short) 1);
        users.setEmail("123456@qq.com");
        users.setPhoneNumber("12345678901");
        users.setIdCard("123456789012345670");
        users.setGender((short) 1);
        users.setImage("C:/Users/G1528/OneDrive/Desktop/龙女妹妹.png");
        users.setStatus((short) 1);
        users.setDelFlag((short) 1);
        users.setLoginTime(LocalDateTime.now());
        users.setCreateTime(LocalDateTime.now());
        users.setCreateBy("tianchang");
        users.setUpdateBy("tianchang");
        users.setUpdateTime(LocalDateTime.now());
        users.setRemark("测试");

        Result result = userService.addUser(users);
        System.out.println(result);
    }
//    @Test
//    public void delete(){
//        System.out.println(userService.deleteUser(1019));
//    }
//
//
//    @Test
//    public void update(){
//        Users users = new Users();
//        users.setUserId(1020);
//        users.setDeptId(3);
//        users.setUserName("tianchang");
//        users.setNickName("天畅");
//        users.setPassword("123456");
//        users.setUserType((short) 1);
//        users.setEmail("123456@qq.com");
//        users.setPhoneNumber("12345678901");
//        users.setIDCard("123456789012345678");
//        Result result = userService.updateUser(users);
//        System.out.println(result);
//    }
//
//    @Resource
//    private RoleService roleService;
//    @Test
//    public void getRoleNameList(){
//        Result result = roleService.getRoleNameList();
//        System.out.println(result);
//    }

    @Resource
    private DeptService deptService;

    @Test
    public void getDeptTree(){
        Result result = deptService.getDeptTree();
        System.out.println(result);
//        Result result = deptService.buildDeptTree();
//        System.out.println(result);
    }
    @Resource
    private RoleService roleService;
    @Test
    public void selectRoles(){
        System.out.println(roleService.getRoleList());
    }

    @Test
    public void getPerms(){
        System.out.println(userService.selectUserPermsById(1));


    }


    @Test
    public void updateDept(){
        Dept dept = new Dept();
        dept.setDeptId(1010);

        System.out.println(dept);

        Result result = deptService.updateDept(dept);

        System.out.println(result);
    }

    @Test
    public void addDept(){
        Dept dept = new Dept();
        dept.setDeptName("技术部");
        dept.setParentId(1000);
        dept.setAncestors("1000");
        dept.setOrderNum(1);
        dept.setLeader("技术部");
        dept.setPhoneNumber("12345678901");
        dept.setEmail("123456@qq.com");
        dept.setStatus((short) 1);
        Result result = deptService.addDept(dept);
        System.out.println(result);
    }
    @Test
    public void deleteDept()
    {
        Result result = roleService.deleteRole(26);
        System.out.println(result);
    }

    @Test
    public void count(){
        Result roleCount = roleService.getRoleCount();
        System.out.println("+++++++++");
        System.out.println(roleCount);
    }
    @Resource
    private PostService postService;

    @Test
public void selectPostList(){
        Result result = postService.queryPostList();
        System.out.println(result);
    }

}
