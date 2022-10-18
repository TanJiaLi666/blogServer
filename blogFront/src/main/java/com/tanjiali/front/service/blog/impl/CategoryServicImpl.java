package com.tanjiali.front.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.front.mapper.blog.CategoryMapper;
import com.tanjiali.front.pojo.blog.Category;
import com.tanjiali.front.service.blog.CategoryServic;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 17:00
 * @Version 1.0
 **/
@Service
public class CategoryServicImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryServic {
}
