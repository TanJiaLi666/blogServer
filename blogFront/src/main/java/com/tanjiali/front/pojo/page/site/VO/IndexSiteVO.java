package com.tanjiali.front.pojo.page.site.VO;

import com.tanjiali.front.pojo.blog.Blog;
import com.tanjiali.front.pojo.blog.Category;
import com.tanjiali.front.pojo.blog.Tag;
import com.tanjiali.front.pojo.page.site.dto.BadgeDTO;
import com.tanjiali.front.pojo.page.site.dto.Introduction;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 首页网页信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 14:19
 * @Version 1.0
 **/
@Data
public class IndexSiteVO {
    private Map<String, Object> siteInfo;
    private Introduction introduction;
    private List<BadgeDTO> badges;
    private List<Blog> newBlogList;
    private List<Category> categoryList;
    private List<Tag> tagList;
    private List<Blog> randomBlogList;
}
