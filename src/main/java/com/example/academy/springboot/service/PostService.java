package com.example.academy.springboot.service;

import com.example.academy.springboot.domain.posts.Posts;
import com.example.academy.springboot.domain.posts.PostsRepositoty;
import com.example.academy.springboot.web.dto.PostUpdateRequestDto;
import com.example.academy.springboot.web.dto.PostsListResponseDto;
import com.example.academy.springboot.web.dto.PostsResponseDto;
import com.example.academy.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostsRepositoty postsRepositoty;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return  postsRepositoty.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Posts posts = postsRepositoty.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepositoty.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepositoty.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepositoty.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepositoty.delete(posts);
    }

}
