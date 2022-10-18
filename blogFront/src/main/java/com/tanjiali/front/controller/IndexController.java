package com.tanjiali.front.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import com.tanjiali.front.pojo.blog.Blog;
import com.tanjiali.front.pojo.page.site.VO.IndexSiteVO;
import com.tanjiali.front.service.SiteSetService;
import com.tanjiali.front.service.blog.BlogService;
import com.tanjiali.front.service.blog.CategoryServic;
import com.tanjiali.front.service.blog.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName 首页系统控制器
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 12:56
 * @Version 1.0
 **/
@RestController
@Api(value = "用户控制器")
@RequestMapping("front/")
public class IndexController {
    @Autowired
    private SiteSetService siteSetService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryServic categoryServic;
    @Autowired
    private TagService tagService;

    @GetMapping("site")
    public PublicResult getSite() {
        IndexSiteVO vo = siteSetService.getSite();
        if (vo == null) {
            return PublicResult.failed("加载失败");
        }
        vo.setCategoryList(categoryServic.list());
        vo.setTagList(tagService.list());
        return PublicResult.success(vo,"加载成功");
    }
    @GetMapping("blogs")
    public PublicResult<PublicPage<Blog>> getBlogList(@RequestParam(value = "pageNum",defaultValue = "1", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5", required = false) Integer pageSize) {
        Page<Blog> blogs = blogService.getBlogList(pageNum, pageSize);
        if (blogs == null) {
            return PublicResult.failed("加载失败");
        }
        return PublicResult.success(PublicPage.restPage(blogs),"加载成功");
    }
}
