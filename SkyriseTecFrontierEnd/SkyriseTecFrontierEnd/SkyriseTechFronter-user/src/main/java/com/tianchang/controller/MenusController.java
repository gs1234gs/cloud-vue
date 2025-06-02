package com.tianchang.controller;

import com.tianchang.configOpenfeign.client.ProductClient;
import com.tianchang.pojo.Menus;
import com.tianchang.queryreceicepojo.QueryMenus;
import com.tianchang.response.Result;
import com.tianchang.service.MenusService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 *
 * @author tianchang
 * 菜单控制层
 *
 * */
@Slf4j
@RequestMapping("/menu")
@RestController
public class MenusController {
    //注入service层
    @Resource
    private MenusService menusService;

    /**
     * 获取用户菜单树
     * @param userId 用户ID
     * @return 菜单树
     */
    @GetMapping("/list/{userId}")
    public Result getMenuTreeByUserId( @PathVariable int userId) {
        Result menuTree = menusService.getMenuTreeByUserId(userId);
        return menuTree;
    }

    //获取菜单列表
    @GetMapping("/list")
    public Result getMenuList() {
        Result menuList = menusService.getMenuList();
        return menuList;
    }

    @Resource
    private RestTemplate restTemplate;

        //注入discoveryClient
//    private final DiscoveryClient discoveryClient;
//    private MenusController(DiscoveryClient discoveryClient) {
//        this.discoveryClient = discoveryClient;
//    }

//    @Resource
    private final ProductClient productClient;
    private MenusController(ProductClient productClient) {
        this.productClient = productClient;
    }


    //添加查询菜单
    @PostMapping("/add")
    public Result addMenu(){
//        String ur = "list";
//        ResponseEntity<List<Object>> exchange = restTemplate.exchange(
//                "http://localhost:9527/product/{ur}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Object>>() {},
//                Map.of("ur",ur)
//        );
//        if(!exchange.getStatusCode().is2xxSuccessful()){
//            return Result.error();
//        }
//        List<Object> body = exchange.getBody();
//       Result result = menusService.addMenu(menu);
//        Random random = new Random();
////       return Result.success(body);
//        //拉取实例列表
//        List<ServiceInstance> goodsApp = discoveryClient.getInstances("GoodsApp");
//        if(goodsApp.isEmpty()){
//            return Result.error();
//        }
//        //负载均衡，随机选择一个实例
//        ServiceInstance serviceInstance = goodsApp.get(random.nextInt(0, goodsApp.size() ));
//                String ur = "list";
//        ResponseEntity<List<Object>> exchange = restTemplate.exchange(
//                serviceInstance.getUri() +"/product/{ur}",//请求路径
//                HttpMethod.GET,//请求方式
//                null,//请求体
//                new ParameterizedTypeReference<List<Object>>() {},//返回类型
//                Map.of("ur",ur)//请求参数
//        );
//        if(!exchange.getStatusCode().is2xxSuccessful()){
//            return Result.error();
//        }
//        List<Object> body = exchange.getBody();
//       Result result = menusService.addMenu(menu);
        List<Object> list = productClient.queryProductList("list");
        log.info("查询商品列表成功{}", list);
        return Result.success(list);
//        return null;
    }

    //修改菜单
    @PutMapping("/update")
    public  Result updateMenu(@RequestBody Menus menu){
        Result result = menusService.updateMenu(menu);
        return result;
    }

    //删除菜单
    @DeleteMapping("/delete/{menuId}")
    public Result deleteMenu(@PathVariable int menuId){
        Result result = menusService.deleteMenu(menuId);
        return result;
    }

    //查询菜单
    @PostMapping("/list")
    public Result queryMenuListByCondition(@RequestBody QueryMenus queryMenu){
        Result result = menusService.queryMenuListByCondition(queryMenu);
        return result;
    }

}
