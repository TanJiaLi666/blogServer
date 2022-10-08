package com.tanjiali.blogadmin.controller.page.friendList;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;
import com.tanjiali.blogadmin.service.page.friendList.FriendListService;
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
    /**
     * export function getFriendInfo() {
     * 	return axios({
     * 		url: 'friendInfo',
     * 		method: 'GET'
     *        })
     * }
     */

 /*
    @ApiOperation("加载列表")
    @GetMapping("/friendInfo")
    public PublicResult<PublicPage<Friend>> getFriendInfo() {
        Page<Friend> friendPage = friendListService.getFriendInfo();
        if (friendPage != null) {
            return PublicResult.success(PublicPage.restPage(friendPage),"成功");
        }
        return PublicResult.failed("失败");
    }*/
}
