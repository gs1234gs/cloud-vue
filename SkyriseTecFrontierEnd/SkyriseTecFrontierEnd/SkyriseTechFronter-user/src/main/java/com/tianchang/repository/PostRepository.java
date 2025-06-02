package com.tianchang.repository;

import com.tianchang.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * repository层接口
 * */

public interface PostRepository
        extends JpaRepository<Post,Integer>,
        JpaSpecificationExecutor<Post> {
}
