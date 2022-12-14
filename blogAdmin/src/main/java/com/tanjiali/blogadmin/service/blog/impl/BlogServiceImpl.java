package com.tanjiali.blogadmin.service.blog.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.BlogMapper;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.pojo.blog.Comment;
import com.tanjiali.blogadmin.pojo.blog.Tag;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCategoryAndTagVO;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogVO;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import com.tanjiali.blogadmin.service.blog.CommentService;
import com.tanjiali.blogadmin.service.blog.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;
    @Override
    public BlogVO blogs(Integer pageNum, Integer pageSize, String title, Integer categoryId) {
        Page<Blog> page = new Page<>(pageNum,pageSize);
        List<Category> categories = categoriesService.list();
        IPage<Blog> blogIPage = this.getBaseMapper().blogs(page, title, categoryId);
        BlogVO blogVO = BlogVO.restPage((Page) blogIPage, categories);
        return blogVO;
    }

    @Override
    public BlogCategoryAndTagVO categoryAndTag() {
        List<Category> categoryList = categoriesService.list();
        List<Tag> tagList = tagService.list();
        return new BlogCategoryAndTagVO(categoryList,tagList);
    }

    @Override
    @Transactional
    public Boolean saveBlog(Blog blog) {
        List<Integer> tagList = blog.getTagList();
        this.save(blog);
        this.getBaseMapper().saveTag(blog.getId(), tagList);
        return true;
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = this.getById(id);
        if (blog == null) {
            return null;
        }
        List<Integer> blogTagList = getBaseMapper().getTagById(id);
        blog.setTagList(blogTagList);
        return blog;
    }

    @Override
    public Boolean updateBlog(Blog blog) {
        List<Integer> tagList = blog.getTagList();
        this.getBaseMapper().saveTag(blog.getId(), tagList);
        return updateById(blog);
    }

    @Override
    @Transactional
    public Boolean deleteBlog(Long id) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getBlogId, id);
        boolean b = removeById(id);
        commentService.remove(wrapper);
        return b;
    }

    @Override
    public Boolean updateRecommend(Long id, Boolean recommend) {
        UpdateWrapper<Blog> wrapper = new UpdateWrapper();
        wrapper.lambda()
                .set(Blog::getRecommend,recommend)
                .eq(Blog::getId, id);
        return update(wrapper);
    }

    @Override
    public Boolean updateTop(Long id, Boolean top) {
        UpdateWrapper<Blog> wrapper = new UpdateWrapper();
        wrapper.lambda()
                .set(Blog::getTop,top)
                .eq(Blog::getId, id);
        return update(wrapper);
    }

    @Override
    public Boolean updateVisibility(Long id, Blog blog) {
        blog.setId(id);
        return updateById(blog);
    }

    @Override
    public List<DashBoardDTO> getCategoryBlogCountList() {
        return baseMapper.getCategoryBlogCountList();
    }

    @Override
    public List<Integer> getBlogTagByid(Integer id) {
        return baseMapper.getBlogTagByid(id);
    }


}
