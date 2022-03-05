package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.CommentRequestDto;
import com.codepresso.codepressoblog.controller.dto.CommentResponseDto;
import com.codepresso.codepressoblog.service.CommentService;
import com.codepresso.codepressoblog.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment")
    public List<CommentResponseDto> getCommentListByPost(@RequestParam Integer postId, @RequestParam Integer page) {
        List<Comment> commentList = commentService.getCommentByPostIdAndPage(postId, page, 3);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody CommentRequestDto commentDto) {
        Comment comment = commentDto.getComment();
        commentService.saveComment(comment);
        return "Success";
    }

    @PutMapping("/comment")
    public String updateComment(@RequestBody CommentRequestDto commentDto) {
        Comment comment = commentDto.getComment();
        commentService.updateComment(comment);
        return "Success";
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Integer commentId) {
        commentService.deleteComment(commentId);
        return "Success";
    }
}
