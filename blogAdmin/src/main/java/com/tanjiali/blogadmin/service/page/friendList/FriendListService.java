package com.tanjiali.blogadmin.service.page.friendList;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;
import com.tanjiali.blogadmin.pojo.page.friendList.vo.FriendListVO;

/**
 * @ClassName FriendListService
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 18:19
 * @Version 1.0
 **/

public interface FriendListService extends IService<Friend> {
    Page<Friend> getFriendsByQuery(Integer pageNum, Integer pageSize);

    FriendListVO getFriendInfo();

    Boolean saveFriend(Friend friend);

    Boolean updateFriend(Friend friend);

    Boolean updatePublished(Long id, Boolean published);

    Boolean deleteFriendById(Long id);

    Boolean updateCommentEnabled(Boolean commentEnabled);

    Boolean updateContent(FriendListVO content);
}
