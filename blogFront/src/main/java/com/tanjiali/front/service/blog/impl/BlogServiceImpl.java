package com.tanjiali.front.service.blog.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.front.mapper.blog.BlogMapper;
import com.tanjiali.front.pojo.blog.Blog;
import com.tanjiali.front.service.blog.BlogService;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 16:04
 * @Version 1.0
 **/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public Page<Blog> getBlogList(Integer pageNum, Integer pageSize) {
        Page<Blog> blogPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getTop, 1)
                .eq(Blog::getPublished, 1)
                .orderByAsc(Blog::getCreateTime);
        return page(blogPage,wrapper);
    }
}
