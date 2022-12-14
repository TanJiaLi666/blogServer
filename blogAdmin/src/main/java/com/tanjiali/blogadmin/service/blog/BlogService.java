package com.tanjiali.blogadmin.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCategoryAndTagVO;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogVO;

import java.util.List;

public interface BlogService extends IService<Blog> {
    BlogVO blogs(Integer pageNum, Integer pageSize, String title, Integer categoryId);

    BlogCategoryAndTagVO categoryAndTag();

    Boolean saveBlog(Blog blog);

    Blog getBlogById(Long id);

    Boolean updateBlog(Blog blog);

    Boolean deleteBlog(Long id);

    Boolean updateRecommend(Long id, Boolean recommend);

    Boolean updateTop(Long id, Boolean top);

    Boolean updateVisibility(Long id, Blog blog);

    List<DashBoardDTO> getCategoryBlogCountList();

    List<Integer> getBlogTagByid(Integer id);
}
