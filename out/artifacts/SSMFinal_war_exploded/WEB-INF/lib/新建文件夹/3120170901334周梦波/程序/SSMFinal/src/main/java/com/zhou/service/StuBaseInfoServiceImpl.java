package com.zhou.service;

import com.zhou.dao.StuBaseInfoMapper;
import com.zhou.pojo.StuBaseInfo;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuBaseInfoServiceImpl implements StuBaseInfoService {

    @Autowired
    @Qualifier("stuBaseInfoMapper")//默认注入小写 约定
    private StuBaseInfoMapper stuBaseInfoMapper;


    //增加一个学生
    public int addStudent(StuBaseInfo stuBaseInfo) {
        return stuBaseInfoMapper.addStudent(stuBaseInfo);
    }

    //查询全部学生
    public List<StuBaseInfo> queryAllStudent() {
        return stuBaseInfoMapper.queryAllStudent();
    }

    //查询
    public  List<StuBaseInfo> queryStudentById(int id) {
        return stuBaseInfoMapper.queryStudentById(id);
    }

    public List<StuBaseInfo> queryStudentByName(String name) {
        return stuBaseInfoMapper.queryStudentByName(name);
    }

    public int updateStudent(StuBaseInfo stuBaseInfo) {
        return stuBaseInfoMapper.updateStudent(stuBaseInfo);
    }

    public int deleteStudentById(int id) {
        return stuBaseInfoMapper.deleteStudentById(id);
    }

}
