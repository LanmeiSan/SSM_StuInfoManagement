package com.zhou.service;

import com.zhou.dao.AdminUserMapper;
import com.zhou.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    @Qualifier("adminUserMapper")
    private AdminUserMapper adminUserMapper;


    public AdminUser queryUser(String adminName) {
        return adminUserMapper.queryUser(adminName);
    }
}
