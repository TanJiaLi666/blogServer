package com.tanjiali.blogadmin.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.admin.AdminUserMapper;
import com.tanjiali.blogadmin.pojo.admin.Admin;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.UserDTO;
import com.tanjiali.blogadmin.service.admin.AdminService;
import com.tanjiali.blogpublicapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminUserMapper, Admin> implements AdminService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public UserDTO login(String name, String pwd) {
        UserDTO dto = new UserDTO();
        Admin admin = this.getOne(new QueryWrapper<Admin>().lambda()
                .eq(Admin::getUsername, name));
        if (admin == null) {
            dto.setToken("");
        }else {
            String token = jwtTokenUtil.generateUserNameStr(admin.getUsername(), admin.getPassword(), admin.getNickname());
            dto.setToken("admin:"+token);
        }
        dto.setUser(admin);
        return dto;
    }

    @Override
    public DashBoardDTO dashboard() {
        return null;
    }
}
