package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.service.CommentService;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.vo.Comment;
import com.codepresso.codepressoblog.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostPageController {

    private final PostService postService;
    private final CommentService commentService;

    @RequestMapping("/post/{id}")
    public String getPostDetailPage(Model model, @PathVariable Integer id) {
        Post post = postService.getPostById(id);
        List<Comment> commentList = commentService.getCommentByPostIdAndPage(id, 1, 3);
        Integer commentCount = commentService.getCommentCount(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);
        model.addAttribute("commentCount", commentCount);
        return "post_detail";
    }

    @RequestMapping("/post/create")
    public String getPostCreatePage() {
        return "post_write";
    }

    @GetMapping("/post/edit/{id}")
    public String getPostCreatePage(Model model, @PathVariable Integer id) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post_edit";
    }
}
