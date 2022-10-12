package com.tanjiali.blogadmin.pojo.admin.vo;

import com.tanjiali.blogadmin.pojo.admin.Admin;
import lombok.Data;

@Data
public class UserDTO {
    private String token;
    private Admin user;
}
