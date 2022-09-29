package com.tanjiali.blogadmin.mapper.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Comment;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogCommentVO;

/**
 * @author tanjiali
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取评论内容
     */
    Page<BlogCommentVO> getCommentListByQuery(Integer page, Integer blogId, IPage<Comment> myPage);


}
