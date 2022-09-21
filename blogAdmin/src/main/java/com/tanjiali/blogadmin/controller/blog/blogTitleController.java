package com.tanjiali.blogadmin.controller.blog;

import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogVO;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCategoryAndTagVO;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "博客文章管理")
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
    @ApiOperation("加载分类与标签")
    @GetMapping("/categoryAndTag")
    public PublicResult categoryAndTag(){
        BlogCategoryAndTagVO blogs= blogService.categoryAndTag();
        return PublicResult.success(blogs);
    }
    @ApiOperation("添加文章")
    @PostMapping("/blog")
    public PublicResult saveBlog(@RequestBody Blog blog){
        Boolean save= blogService.saveBlog(blog);
        if (save) {
            return PublicResult.success("保存成功");
        }
        return PublicResult.failed("保存失败");
    }
    @ApiOperation("获取文章")
    @GetMapping("/blog")
    public PublicResult getBlogById(@RequestParam("id") Integer id){
        Blog blog= blogService.getBlogById(id);
        if (blog == null) {
            PublicResult.failed("查询失败，请检查网络后重试！！");
        }
        return PublicResult.success(blog,"查询成功");
    }
}
