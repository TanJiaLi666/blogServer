package com.tanjiali.blogadmin.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Moment;
import com.tanjiali.blogadmin.service.blog.MomentService;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "博客动态管理")
@RequestMapping("back/blog")
@CrossOrigin
public class BlogMomentController {
    @Autowired
    private MomentService momentService;
    @ApiOperation("动态分类")
    @GetMapping("/moments")
    public PublicResult<PublicPage<Moment>> moments(@RequestParam(value = "pageNum") Integer pageNum,
                                              @RequestParam(value = "pageSize") Integer pageSize){
        Page<Moment> moments = momentService.moments(pageNum,pageSize);
        return PublicResult.success(PublicPage.restPage(moments));
    }
    @ApiOperation("添加动态")
    @PostMapping("/moment")
    public PublicResult<Boolean> addMoment(@RequestBody Moment moment){
        Boolean success = momentService.addMoment(moment);
        if (success) {
            return PublicResult.success(true,"添加成功！！");
        }
        return PublicResult.failed("添加失败！");
    }
    @ApiOperation("编辑动态")
    @PutMapping("/moment")
    public PublicResult<Boolean> editMoment(@RequestBody Moment moment){
        Boolean success = momentService.editMoment(moment);
        if (success) {
            return PublicResult.success(true,"编辑成功！！");
        }
        return PublicResult.failed("编辑失败！");
    }
    @ApiOperation("删除动态")
    @DeleteMapping("/moment")
    public PublicResult<Boolean> deleteMoment(@RequestParam("id") Integer id){
        Boolean success = momentService.deleteMoment(id);
        if (success) {
            return PublicResult.success(true,"删除成功！！");
        }
        return PublicResult.failed("删除失败！");
    }
    @ApiOperation("发布动态")
    @PutMapping("/moment/published")
    public PublicResult<Boolean> publishedMoment(@RequestParam("id") Integer id,
                                                 @RequestParam("published") Boolean published){
        Boolean success = momentService.publishedMoment(id, published);
        if (success && published) {
            return PublicResult.success(true,"发布成功！！");
        }
        return PublicResult.success("未发布！");
    }
    @ApiOperation("动态详细信息")
    @GetMapping("/moment")
    public PublicResult<Moment> getByIdMoment(@RequestParam("id")  Integer id){
        Moment success = momentService.getByIdMoment(id);
        return PublicResult.success(success,"未发布！");
    }
}
