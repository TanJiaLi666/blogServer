package com.tanjiali.front.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.front.mapper.blog.TagMapper;
import com.tanjiali.front.pojo.blog.Tag;
import com.tanjiali.front.service.blog.TagService;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 17:03
 * @Version 1.0
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
