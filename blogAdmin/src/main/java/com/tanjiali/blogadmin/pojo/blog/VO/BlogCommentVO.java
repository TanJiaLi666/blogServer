package com.tanjiali.blogadmin.pojo.blog.VO;

import com.tanjiali.blogadmin.pojo.blog.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author tanjiali
 */
@Data
public class BlogCommentVO extends Comment{
    BlogIdAndTitleVO blog;
    List<Comment> replyComments;
}
