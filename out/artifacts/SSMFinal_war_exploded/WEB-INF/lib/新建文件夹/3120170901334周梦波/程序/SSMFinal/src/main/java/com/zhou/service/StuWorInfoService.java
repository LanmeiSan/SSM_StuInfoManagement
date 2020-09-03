package com.zhou.service;

import com.zhou.pojo.StuWorInfo;

import java.util.List;

public interface StuWorInfoService {
    //增加一个学生信息
    int addStudentWorInfo(StuWorInfo stuWorInfo);

    //查询一个学生获奖信息
    List<StuWorInfo> queryStudentWorInfoById(int id);

    //查询一个学生获奖信息
    List<StuWorInfo> queryStudentWorInfoByName(String name);

    //修改一个学生获奖信息
    int updateStudentWorInfo(StuWorInfo stuWorInfo);

    //删除一个学生获奖信息
    int deleteStudentWorInfoById(int id);

    //查询全部学生获奖信息
    List<StuWorInfo> queryAllStudentWorInfo();
}
