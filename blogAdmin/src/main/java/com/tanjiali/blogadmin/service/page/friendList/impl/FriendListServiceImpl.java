package com.tanjiali.blogadmin.service.page.friendList.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.page.friendList.FriendMapper;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;
import com.tanjiali.blogadmin.service.page.friendList.FriendListService;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 18:25
 * @Version 1.0
 **/
@Service
public class FriendListServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendListService {
    @Override
    public Page<Friend> getFriendsByQuery(Integer pageNum, Integer pageSize) {
        Page<Friend> page = new Page<>(pageNum, pageSize);
        return page(page);
    }
}
