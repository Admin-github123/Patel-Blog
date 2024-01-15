package com.blog.patelBlog.service.impl;

import com.blog.patelBlog.entity.Post;
import com.blog.patelBlog.exception.ResourceNotFoundException;
import com.blog.patelBlog.payload.PostDto;
import com.blog.patelBlog.repository.PostRepository;
import com.blog.patelBlog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savePost = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setId(savePost.getId());
        dto.setTitle(savePost.getTitle());
        dto.setContent(savePost.getContent());
        dto.setDescription(savePost.getDescription());
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + id)
        );
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return dto;
    }

    @Override
    public String deletePostById(long id) {
         postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with this id" + id)
        );
        postRepository.deleteById(id);
        return "record is deleted!!";
    }

    @Override
    public PostDto updatePost(PostDto postDto ,Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setContent(postDto.getContent());

            Post updatePost = postRepository.save(post);
            PostDto dto=new PostDto();
            dto.setId(updatePost.getId());
            dto.setTitle(updatePost.getTitle());
            dto.setDescription(updatePost.getDescription());
            dto.setContent(updatePost.getContent());
            return dto;
        }

        return null;
    }
}
