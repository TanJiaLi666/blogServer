package com.tanjiali.blogadmin.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "博客分类管理")
@RequestMapping("back/blog")
@CrossOrigin
public class BlogCategoryController {
    @Autowired
    private CategoriesService categoriesService;

    @ApiOperation("加载分类")
    @GetMapping("/categories")
    public PublicResult<PublicPage<Category>> categories(@RequestParam(value = "pageNum") Integer pageNum,
                                       @RequestParam(value = "pageSize") Integer pageSize){
        Page<Category> category = categoriesService.categories(pageNum,pageSize);
        return PublicResult.success(PublicPage.restPage(category));
    }
    @ApiOperation("添加分类")
    @OperaLog("添加分类")
    @PostMapping("/category")
    public PublicResult<Boolean> addCategory(@RequestBody Category category){
        Boolean success = categoriesService.addCategory(category);
        if (success) {
            return PublicResult.success(true,"添加成功！！");
        }
        return PublicResult.failed("添加失败！");
    }
    @ApiOperation("编辑分类")
    @OperaLog("编辑分类")
    @PutMapping("/category")
    public PublicResult<Boolean> editCategory(@RequestBody Category category){
        Boolean success = categoriesService.editCategory(category);
        if (success) {
            return PublicResult.success(true,"编辑成功！！");
        }
        return PublicResult.failed("编辑失败！");
    }
    @ApiOperation("删除分类")
    @OperaLog("删除分类")
    @DeleteMapping("/category")
    public PublicResult<Boolean> deleteCategory(@RequestParam("id") Integer id){
        Boolean success = categoriesService.deleteCategory(id);
        if (success) {
            return PublicResult.success(true,"删除成功！！");
        }
        return PublicResult.failed("删除失败！");
    }
}
