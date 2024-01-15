package com.blog.patelBlog.service;

import com.blog.patelBlog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    String deletePostById(long id);
}
