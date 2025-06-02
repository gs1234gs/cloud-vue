package com.tianchang.controller;

import com.tianchang.response.Result;
import com.tianchang.service.PostService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;
    //获取岗位列表
    @GetMapping("/list")
    public Result queryPostList(){
       Result result = postService.queryPostList();
       return result;
    }
}
