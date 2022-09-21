package com.tanjiali.blogadmin.pojo.blog.VO;

import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.pojo.blog.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author tanjiali
 * @version 1.0
 * @data 2022/9/20 15:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCategoryAndTagVO {
    List<Category> categories;
    List<Tag> tags;
}
