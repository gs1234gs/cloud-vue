package com.tianchang.service.impl;

import com.tianchang.repository.PostRepository;
import com.tianchang.response.Result;
import com.tianchang.service.PostService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Resource
    private PostRepository postRepository;

    //查询岗位列表
    @Override
    public Result queryPostList() {
        return Result.builder()
                .code(200)
                .msg("查询岗位列表成功")
                .data(postRepository.findAll())
                .build();
    }
}
