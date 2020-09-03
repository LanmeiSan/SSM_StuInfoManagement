package com.zhou.service;

import com.zhou.dao.TeacherUserMapper;
import com.zhou.pojo.TeacherUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TeacherUserServiceImpl implements TeacherUserService {

    @Autowired
    @Qualifier("teacherUserMapper")
    private TeacherUserMapper teacherUserMapper;

    public TeacherUser queryUser(String teaName) {
        return teacherUserMapper.queryUser(teaName);
    }
}
