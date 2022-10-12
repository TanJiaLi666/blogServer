package com.tanjiali.blogadmin.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.admin.Admin;
import com.tanjiali.blogadmin.pojo.admin.vo.DashBoardVO;
import com.tanjiali.blogadmin.pojo.admin.vo.UserDTO;

public interface AdminService extends IService<Admin> {
    /**
     * dl
     * @param name
     * @param pwd
     * @return
     */
    UserDTO login(String name, String pwd);

    DashBoardVO dashboard();

}
