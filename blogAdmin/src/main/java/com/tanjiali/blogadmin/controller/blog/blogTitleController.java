package com.tanjiali.blogadmin.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogVO;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "博客标签管理")
@RequestMapping("back/blog")
@CrossOrigin
public class blogTitleController {
    @Autowired
    private BlogService blogService;

    @ApiOperation("文章列表")
    @GetMapping("/blogs")
    public PublicResult blogs(@RequestParam(value = "pageNum") Integer pageNum,
                                                @RequestParam(value = "pageSize") Integer pageSize,
                                                @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                                @RequestParam(value = "title", required = false) String title){
        BlogVO blogs= blogService.blogs(pageNum,pageSize,title,categoryId);
        return PublicResult.success(blogs);
    }

}
