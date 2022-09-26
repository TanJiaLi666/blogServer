package com.tanjiali.blogadmin.controller;

import cn.hutool.core.util.StrUtil;
import com.tanjiali.blogadmin.pojo.admin.Admin;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.UserDTO;

import com.tanjiali.blogadmin.service.admin.AdminService;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "用户控制器")
@RequestMapping("back/admin")
@CrossOrigin
public class UserController {
    @Autowired
    private AdminService adminService;


    @ApiOperation("用户登录")
    @PostMapping("/login")
    public PublicResult<UserDTO> login(@RequestBody Admin user){
        UserDTO dto = adminService.login(user.getUsername(), user.getPassword());
        if (StrUtil.isEmpty(dto.getToken())) {
            return PublicResult.failed("failed");
        }
        return PublicResult.success(dto,"登录成功!!");
    }

    @ApiOperation("表盘数据")
    @GetMapping("/dashboard")
    public PublicResult<DashBoardDTO> dashboard(){
        DashBoardDTO data = adminService.dashboard();
        return PublicResult.success(data);
    }
}
