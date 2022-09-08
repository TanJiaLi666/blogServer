package com.tanjiali.blogadmin.controller.blog;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.admin.Admin;
import com.tanjiali.blogadmin.pojo.admin.dto.UserDTO;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "博客管理")
@RequestMapping("back/blog")
@CrossOrigin
public class blogManageController {
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
    @PostMapping("/category")
    public PublicResult<Boolean> category(@RequestBody Category category){
        Boolean success = categoriesService.category(category);
        if (success) {
            return PublicResult.success(true,"添加成功！！");
        }
        return PublicResult.failed("添加失败！");
    }
}
