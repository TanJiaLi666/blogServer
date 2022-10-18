package com.tanjiali.blogadmin.controller.page.friendList;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;
import com.tanjiali.blogadmin.pojo.page.friendList.vo.FriendListVO;
import com.tanjiali.blogadmin.service.page.friendList.FriendListService;
import com.tanjiali.blogpublicapi.annotation.LoginCheck;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FriendListController
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 18:13
 * @Version 1.0
 **/
@RestController
@Api(value = "友链页面管理")
@RequestMapping("back/page")
@CrossOrigin
public class FriendListController {

    @Autowired
    private FriendListService friendListService;

    @ApiOperation("加载列表")
    @GetMapping("/friends")
    public PublicResult<PublicPage<Friend>> getFriendsByQuery(@RequestParam("pageNum") Integer pageNum,
                                                              @RequestParam("pageSize") Integer pageSize) {
        Page<Friend> friendPage = friendListService.getFriendsByQuery(pageNum, pageSize);
        if (friendPage != null) {
            return PublicResult.success(PublicPage.restPage(friendPage),"成功");
        }
        return PublicResult.failed("失败");
    }

    @ApiOperation("加载友链信息")
    @GetMapping("/friendInfo")
    public PublicResult<FriendListVO> getFriendInfo() {
        FriendListVO friendInfo = friendListService.getFriendInfo();
        if (friendInfo != null) {
            return PublicResult.success(friendInfo,"成功");
        }
        return PublicResult.failed("失败");
    }
    @ApiOperation("保存友链信息")
    @OperaLog("保存友链信息")
    @PostMapping("/friend")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> saveFriend(@RequestBody Friend friend) {
        Boolean save = friendListService.saveFriend(friend);
        if (save) {
            return PublicResult.success(true,"保存成功");
        }
        return PublicResult.failed("失败");
    }
    @ApiOperation("更新友链信息")
    @OperaLog("更新友链信息")
    @PutMapping("/friend")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> updateFriend(@RequestBody Friend friend) {
        Boolean updateFriend = friendListService.updateFriend(friend);
        if (updateFriend) {
            return PublicResult.success(true,"更新成功");
        }
        return PublicResult.failed("失败");
    }

    @ApiOperation("更新友链公开信息")
    @OperaLog("更新友链公开信息")
    @PutMapping("/friend/published")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> updatePublished(@RequestParam("id") Long id,
                                                 @RequestParam("published") Boolean published) {
        Boolean updateFriend = friendListService.updatePublished(id, published);
        if (updateFriend) {
            return PublicResult.success(true,"更新成功");
        }
        return PublicResult.failed("失败");
    }
    @ApiOperation("删除友链")
    @OperaLog("删除友链")
    @DeleteMapping("/friend")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> deleteFriendById(@RequestParam("id") Long id) {
        Boolean updateFriend = friendListService.deleteFriendById(id);
        if (updateFriend) {
            return PublicResult.success(true,"更新成功");
        }
        return PublicResult.failed("失败");
    }
    @ApiOperation("更新友链信息公开")
    @OperaLog("更新友链评论信息公开")
    @PutMapping("friendInfo/commentEnabled")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> updateCommentEnabled(@RequestParam("commentEnabled") Boolean commentEnabled) {
        Boolean updateFriend = friendListService.updateCommentEnabled(commentEnabled);
        if (updateFriend) {
            return PublicResult.success(true,"更新成功");
        }
        return PublicResult.failed("失败");
    }
    @ApiOperation("更新友链公开信息")
    @OperaLog("更新友链公开信息内容")
    @PutMapping("friendInfo/content")
    @LoginCheck("用户需要登录验证")
    public PublicResult<Boolean> updateContent(@RequestBody FriendListVO friendListVO) {
        Boolean updateFriend = friendListService.updateContent(friendListVO);
        if (updateFriend) {
            return PublicResult.success(true,"更新成功");
        }
        return PublicResult.failed("失败");
    }
}
