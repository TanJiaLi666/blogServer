package com.tanjiali.blogadmin.pojo.blog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tanjiali
 * @version 1.0
 * @data 2022/9/20 15:33
 */
@Data
@TableName("blog_tag")
public class BlogTag {
    @TableField("blog_id")
    private Integer blogId;
    @TableField("tag_id")
    private Integer tagId;
}
