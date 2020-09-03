package com.zhou.service;

import com.zhou.dao.StuWorInfoMapper;
import com.zhou.pojo.StuWorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuWorInfoServiceImpl implements StuWorInfoService {

    @Autowired
    @Qualifier("stuWorInfoMapper")
    private StuWorInfoMapper stuWorInfoMapper;

    public int addStudentWorInfo(StuWorInfo stuWorInfo) {
        return stuWorInfoMapper.addStudentWorInfo(stuWorInfo);
    }

    public List<StuWorInfo> queryStudentWorInfoById(int id) {
        return stuWorInfoMapper.queryStudentWorInfoById(id);
    }

    public List<StuWorInfo> queryStudentWorInfoByName(String name) {
        return stuWorInfoMapper.queryStudentWorInfoByName(name);
    }

    public int updateStudentWorInfo(StuWorInfo stuWorInfo) {
        return stuWorInfoMapper.updateStudentWorInfo(stuWorInfo);
    }

    public int deleteStudentWorInfoById(int id) {
        return stuWorInfoMapper.deleteStudentWorInfoById(id);
    }

    public List<StuWorInfo> queryAllStudentWorInfo() {
        return stuWorInfoMapper.queryAllStudentWorInfo();
    }
}
