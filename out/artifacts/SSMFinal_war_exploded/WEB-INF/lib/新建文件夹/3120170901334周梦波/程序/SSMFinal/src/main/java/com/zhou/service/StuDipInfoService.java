package com.zhou.service;

import com.zhou.pojo.StuDipInfo;

import java.util.List;

public interface StuDipInfoService {
    //增加一个学生获奖信息
    int addStudentDipInfo(StuDipInfo stuDipInfo);

    //查询一个学生获奖信息
    List<StuDipInfo> queryStudentDipInfoById(int id);

    //查询一个学生获奖信息
    List<StuDipInfo> queryStudentDipInfoByName(String name);

    //修改一个学生获奖信息
    int updateStudentDipInfo(StuDipInfo stuDipInfo);

    //删除一个学生获奖信息
    int deleteStudentDipInfoById(int id);

    //查询全部学生获奖信息
    List<StuDipInfo> queryAllStudentDipInfo();
}
