package com.codepresso.codepressoblog.controller;


import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Post> postList = postService.getPostByPage(1, 3);
        model.addAttribute("posts", postList);
        return "index";
    }
}
