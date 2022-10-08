package com.tanjiali.blogadmin.service.page.friendList.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.page.friendList.FriendMapper;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;
import com.tanjiali.blogadmin.pojo.page.friendList.vo.FriendListVO;
import com.tanjiali.blogadmin.pojo.page.site.SiteSetting;
import com.tanjiali.blogadmin.service.page.friendList.FriendListService;
import com.tanjiali.blogadmin.service.page.site.SiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 18:25
 * @Version 1.0
 **/
@Service
public class FriendListServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendListService {
    @Autowired
    private SiteSettingService siteSettingService;
    @Override
    public Page<Friend> getFriendsByQuery(Integer pageNum, Integer pageSize) {
        Page<Friend> page = new Page<>(pageNum, pageSize);
        return page(page);
    }

    @Override
    public FriendListVO getFriendInfo() {
        FriendListVO vo = new FriendListVO();
        QueryWrapper<SiteSetting> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(SiteSetting::getType, 4);
        List<SiteSetting> list = siteSettingService.list(wrapper);
        list.stream().map(o->{
            if (o.getNameEn().contains("friendContent")) {
                vo.setContent(o.getValue());
            }
            boolean flag = o.getNameEn().contains("friendCommentEnabled");
            if (flag) {
                String value = o.getValue();
                if ("1".equals(value)) {
                    vo.setCommentEnabled(true);
                }else {
                    vo.setCommentEnabled(false);
                }
            }
            return o;
        }).collect(Collectors.toList());
        return vo;
    }

    @Override
    public Boolean saveFriend(Friend friend) {
        friend.setViews(0);
        return save(friend);
    }

    @Override
    public Boolean updateFriend(Friend friend) {
        return updateById(friend);
    }

    @Override
    public Boolean updatePublished(Long id, Boolean published) {
        UpdateWrapper<Friend> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(Friend::getPublished, published)
                .eq(Friend::getId, id);
        return update(wrapper);
    }

    @Override
    public Boolean deleteFriendById(Long id) {
        return removeById(id);
    }

    @Override
    public Boolean updateCommentEnabled(Boolean commentEnabled) {
        UpdateWrapper<SiteSetting> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(SiteSetting::getValue, commentEnabled)
                .eq(SiteSetting::getType, 4)
                .eq(SiteSetting::getNameEn, "friendCommentEnabled");
        return siteSettingService.update(wrapper);
    }

    @Override
    public Boolean updateContent(FriendListVO friendListVO) {
        UpdateWrapper<SiteSetting> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(SiteSetting::getValue, friendListVO.getContent())
                .eq(SiteSetting::getType, 4)
                .eq(SiteSetting::getNameEn, "friendContent");
        return siteSettingService.update(wrapper);
    }
}
