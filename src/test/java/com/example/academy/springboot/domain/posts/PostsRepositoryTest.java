package com.example.academy.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepositoty postsRepositoty;

    @After
    public void clear(){
        postsRepositoty.deleteAll();
    }

    @Test
    public void saveArticle(){
        String title = "테스트 게시글 제목";
        String content = "테스트 게시글 본문";

        postsRepositoty.save(Posts.builder().title(title).content(content).author("ltk@naver.com").build());

        List<Posts> postsList = postsRepositoty.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }



}
