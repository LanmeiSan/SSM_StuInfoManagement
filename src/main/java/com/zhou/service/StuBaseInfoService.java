package com.zhou.service;

import com.zhou.pojo.StuBaseInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface StuBaseInfoService {


    //增加一个学生
    int addStudent(StuBaseInfo stuBaseInfo);

    //查询全部学生
    List<StuBaseInfo> queryAllStudent();

    //查询一个学生
    List<StuBaseInfo> queryStudentById(int id);

    //查询一个学生
    List<StuBaseInfo> queryStudentByName(String name);

    //修改一个学生
    int updateStudent(StuBaseInfo stuBaseInfo);

    //删除一个学生
    int deleteStudentById(int id);


}
