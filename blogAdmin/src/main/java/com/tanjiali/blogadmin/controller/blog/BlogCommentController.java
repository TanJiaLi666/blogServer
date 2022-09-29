package com.tanjiali.blogadmin.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Comment;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCommentVO;
import com.tanjiali.blogadmin.service.blog.CommentService;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "博客评论管理")
@RequestMapping("back/blog")
@CrossOrigin
public class BlogCommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation("评论列表")
    @GetMapping("/comments")
    public PublicResult<PublicPage<BlogCommentVO>> getCommentListByQuery( Comment comment){
        Page<BlogCommentVO> comments= commentService.getCommentListByQuery(comment);
        if (comments != null) {
            return PublicResult.success(PublicPage.restPage(comments),"加载成功");
        }
        return PublicResult.failed("加载失败，请检查网络");
    }
    @ApiOperation("博客列表")
    @GetMapping("/blogIdAndTitle")
    public PublicResult<List<Blog>> getBlogList(){
        List<Blog> blogs= commentService.getBlogList();
        if (blogs != null) {
            return PublicResult.success(blogs,"加载成功");
        }
        return PublicResult.failed("加载失败，请检查网络");
    }
    @ApiOperation("修改评论")
    @PutMapping("/comment")
    public PublicResult<Boolean> editComment(@RequestBody Comment comment){
        Boolean update= commentService.editComment(comment);
        if (update) {
            return PublicResult.success(true,"修改成功");
        }
        return PublicResult.failed("修改失败，请检查网络");
    }
    @ApiOperation("删除评论")
    @DeleteMapping("/comment")
    public PublicResult<Boolean> deleteCommentById(Long id){
        Boolean update= commentService.deleteCommentById(id);
        if (update) {
            return PublicResult.success(true,"删除成功");
        }
        return PublicResult.failed("删除失败，请检查网络");
    }
    @ApiOperation("公开评论")
    @PutMapping("/comment/published")
    public PublicResult<Boolean> updatePublished(@RequestParam("id") Long id,
                                                 @RequestParam("published") Boolean published ){
        Boolean update = commentService.updateButton(Comment::getPublished, id, published);
        if (update) {
            return PublicResult.success(true,"成功");
        }
        return PublicResult.failed("失败，请检查网络");
    }
    @ApiOperation("公开评论")
    @PutMapping("/comment/notice")
    public PublicResult<Boolean> updateNotice(@RequestParam("id") Long id,
                                              @RequestParam("notice") Boolean notice){
        Boolean update = commentService.updateButton(Comment::getNotice, id, notice);
        if (update) {
            return PublicResult.success(true,"成功");
        }
        return PublicResult.failed("失败，请检查网络");
    }
}
