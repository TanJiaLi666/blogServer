package com.tanjiali.blogadmin.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.pojo.blog.Tag;
import com.tanjiali.blogadmin.service.blog.TagService;
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
public class BlogTagController {
    @Autowired
    private TagService tagService;

    @ApiOperation("标签分类")
    @GetMapping("/tags")
    public PublicResult<PublicPage<Tag>> tags(@RequestParam(value = "pageNum") Integer pageNum,
                                              @RequestParam(value = "pageSize") Integer pageSize){
        Page<Tag> tags = tagService.tags(pageNum,pageSize);
        return PublicResult.success(PublicPage.restPage(tags));
    }
    @ApiOperation("添加标签")
    @PostMapping("/tag")
    public PublicResult<Boolean> addTag(@RequestBody Tag tag){
        Boolean success = tagService.addTag(tag);
        if (success) {
            return PublicResult.success(true,"添加成功！！");
        }
        return PublicResult.failed("添加失败！");
    }
    @ApiOperation("编辑标签")
    @PutMapping("/tag")
    public PublicResult<Boolean> editTag(@RequestBody Tag tag){
        Boolean success = tagService.editTag(tag);
        if (success) {
            return PublicResult.success(true,"编辑成功！！");
        }
        return PublicResult.failed("编辑失败！");
    }
    @ApiOperation("删除标签")
    @DeleteMapping("/tag")
    public PublicResult<Boolean> deleteTag(@RequestParam("id") Integer id){
        Boolean success = tagService.deleteTag(id);
        if (success) {
            return PublicResult.success(true,"删除成功！！");
        }
        return PublicResult.failed("删除失败！");
    }
}
