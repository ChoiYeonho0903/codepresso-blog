package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.PostRequestDto;
import com.codepresso.codepressoblog.controller.dto.PostResponseDto;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public List<PostResponseDto> getPostList(@RequestParam Integer page) {
        List<Post> postList = postService.getPostByPage(page, 3);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostRequestDto postDto) {
        Post post = postDto.getPost();
        postService.savePost(post);

        return "success";
    }

    @PutMapping("/post")
    public String updatePost(@RequestBody PostRequestDto postDto) {
        Post post = postDto.getPost();
        postService.updatePost(post);
        return "success";
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam Integer postId) {
        postService.deletePost(postId);
        return "success";
    }

}
