package com.tanjiali.blogadmin.service.page.friendList;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;

/**
 * @ClassName FriendListService
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 18:19
 * @Version 1.0
 **/

public interface FriendListService extends IService<Friend> {
    Page<Friend> getFriendsByQuery(Integer pageNum, Integer pageSize);
}
