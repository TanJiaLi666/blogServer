package com.tanjiali.blogpublicapi.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 分页数据结果集
 * @param <T>
 */
@Data
public class PublicPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     */
    public static <T> PublicPage<T> restPage(Page<T> pageResult) {
        PublicPage<T> result = new PublicPage<>();
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
        return result;
    }
}
