package com.tanjiali.blogadmin.service.blog.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.CommentMapper;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Comment;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCommentVO;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogIdAndTitleVO;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogadmin.service.blog.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl  extends ServiceImpl<CommentMapper, Comment> implements CommentService{
    @Autowired
    private BlogService blogService;
    @Override
    public Page<BlogCommentVO> getCommentListByQuery(Comment comment) {
        Page<Comment> page = new Page<>(comment.getPageNum(), comment.getPageSize());
        Page<BlogCommentVO> list = baseMapper.getCommentListByQuery(comment.getPage(), comment.getBlogId(), page);
        List<BlogCommentVO> records = list.getRecords();
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        records = records.stream().map(o -> {
            if (o.getBlogId() == null) {
                return o;
            }
            wrapper.lambda()
                    .select(Blog::getId, Blog::getTitle)
                    .eq(Blog::getId, o.getBlogId());
            Blog blog = blogService.getOne(wrapper);
            if (blog ==null) {
                return o;
            }
            BlogIdAndTitleVO vo = new BlogIdAndTitleVO();
            vo.setId(blog.getId());
            vo.setTitle(blog.getTitle());
            o.setBlog(vo);
            return o;
        }).collect(Collectors.toList());
        list.setRecords(records);
        return list;
    }

    @Override
    public List<Blog> getBlogList() {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .select(Blog::getId,Blog::getTitle);
        return blogService.list(wrapper);
    }

    @Override
    public Boolean editComment(Comment comment) {
        return updateById(comment);
    }

    @Override
    public Boolean deleteCommentById(Long id) {
        return removeById(id);
    }

    @Override
    public Boolean updateButton(SFunction<Comment,?> function, Long id, Boolean notice) {
        UpdateWrapper<Comment> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(function, notice)
                .eq(Comment::getId, id);
        return update(wrapper);
    }
}
