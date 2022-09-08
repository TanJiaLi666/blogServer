package com.tanjiali.blogadmin.pojo.blog.VO;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.blog.Category;
import lombok.Data;

import java.util.List;

@Data
public class BlogVO<T>  {
    private List<Category> categories;

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     */
    public static <T> BlogVO<T> restPage(Page<T> pageResult, List<Category> categories) {
        BlogVO<T> result = new BlogVO<>();
        // 当前页
        result.setPageNum(Convert.toInt(pageResult.getCurrent()));
        // 一页多少条数据
        result.setPageSize(Convert.toInt(pageResult.getSize()));
        // 总数据数量
        result.setTotal(pageResult.getTotal());
        // 总页数
        result.setTotalPage(Convert.toInt(pageResult.getTotal()/pageResult.getSize()+1));
        // 当前页数据
        result.setList(pageResult.getRecords());
        result.setCategories(categories);
        return result;
    }
}

