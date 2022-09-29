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
public class BlogTitleController {
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
    public PublicResult getBlogById(@RequestParam("id") Long id){
        Blog blog= blogService.getBlogById(id);
        if (blog == null) {
            PublicResult.failed("查询失败，请检查网络后重试！！");
        }
        return PublicResult.success(blog,"查询成功");
    }
    @ApiOperation("更新文章")
    @PutMapping("/blog")
    public PublicResult updateBlog(@RequestBody Blog blog){
        Boolean update= blogService.updateBlog(blog);
        if (!update) {
            PublicResult.failed("更新失败，请检查网络后重试！！");
        }
        return PublicResult.success(true,"保存成功");
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/blog")
    public PublicResult deleteBlog(@RequestParam("id") Long id){
        Boolean delete= blogService.deleteBlog(id);
        if (!delete) {
            PublicResult.failed("删除失败，请检查网络后重试！！");
        }
        return PublicResult.success(true,"删除成功");
    }
    @ApiOperation("推荐文章")
    @PutMapping("/blog/recommend")
    public PublicResult updateRecommend(@RequestParam("id") Long id,
                                        @RequestParam("recommend") Boolean recommend){
        Boolean update= blogService.updateRecommend(id,recommend);
        if (!update) {
            PublicResult.failed("失败，请检查网络后重试！！");
        }
        return PublicResult.success(true,"成功");
    }
    @ApiOperation("置顶文章")
    @PutMapping("/blog/top")
    public PublicResult updateTop(@RequestParam("id") Long id,
                                        @RequestParam("top") Boolean top){
        Boolean update= blogService.updateTop(id,top);
        if (!update) {
            PublicResult.failed("失败，请检查网络后重试！！");
        }
        return PublicResult.success(true,"成功");
    }
    @ApiOperation("文章可见性")
    @PutMapping("/{id}/visibility")
    public PublicResult updateVisibility(@PathVariable("id") Long id ,
                                         @RequestBody Blog blog){
        Boolean update= blogService.updateVisibility(id,blog);
        if (!update) {
            PublicResult.failed("失败，请检查网络后重试！！");
        }
        return PublicResult.success(true,"成功");
    }
}
