package com.tanjiali.blogadmin.service.blog;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Comment;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCommentVO;

import java.util.List;


public interface CommentService extends IService<Comment> {
    Page<BlogCommentVO> getCommentListByQuery(Comment comment);

    List<Blog> getBlogList();

    Boolean editComment(Comment comment);

    Boolean deleteCommentById(Long id);

    Boolean updateButton(SFunction<Comment, ?> column,Long id, Boolean notice);
}
