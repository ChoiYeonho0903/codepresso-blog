package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.CommentMapper;
import com.codepresso.codepressoblog.vo.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getCommentByPostIdAndPage (Integer postId, Integer page, Integer size) {
        return commentMapper.findByPostIdAndPage(postId, size, (page-1)*size);
    }

    public Integer getCommentCount(Integer postId) {
        Integer commentCount = commentMapper.count(postId);
        return commentCount;
    }
    public boolean saveComment (Comment comment) {
        Integer result = commentMapper.save(comment);
        return result == 1;
    }

    public boolean updateComment (Comment comment) {
        Integer result = commentMapper.update(comment);
        return result == 1;
    }

    public boolean deleteComment (@RequestParam Integer commentId) {
        Integer result = commentMapper.delete(commentId);
        return result == 1;
    }

}
