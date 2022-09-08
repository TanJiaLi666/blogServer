package com.tanjiali.blogadmin.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.admin.Admin;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.UserDTO;

public interface AdminService extends IService<Admin> {
    /**
     * dl
     * @param name
     * @param pwd
     * @return
     */
    UserDTO login(String name, String pwd);

    DashBoardDTO dashboard();

}
