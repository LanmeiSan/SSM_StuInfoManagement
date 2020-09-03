package com.zhou.service;

import com.zhou.pojo.StuFamInfo;

import java.util.List;

public interface StuFamInfoService {

    //增加一个学生信息
    int addStudentFamInfo(StuFamInfo stuFamInfo);

    //查询一个学生家庭信息
    List<StuFamInfo> queryStudentFamInfoById(int id);

    //查询一个学生家庭信息
    List<StuFamInfo> queryStudentFamInfoByName(String name);

    //修改一个学生家庭信息
    int updateStudentFamInfo(StuFamInfo stuFamInfo);

    //删除一个学生家庭信息
    int deleteStudentInfoById(int id);

    //查询全部学生家庭信息
    List<StuFamInfo> queryAllStudentFamInfo();
}
