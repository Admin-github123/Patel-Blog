package com.blog.patelBlog.repository;

import com.blog.patelBlog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
