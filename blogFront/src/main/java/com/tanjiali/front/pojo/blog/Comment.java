package com.tanjiali.front.pojo.blog;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Long id;
    private String nickname;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;//评论时间
    private String ip;//评论者ip地址
    @TableField("is_published")
    private Boolean published;//公开或回收站
    @TableField("is_admin_comment")
    private Boolean adminComment;//博主回复
    @TableField("is_notice")
    private Boolean notice;//接收邮件提醒
    @TableField("blog_id")
    private Integer blogId;
    @TableField("parent_comment_id")
    private Long parentCommentId;//父评论id
    private String website;//个人网站
    private Integer page;//0普通文章，1关于我页面
    private String qq;//如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份

    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer pageSize;
}
