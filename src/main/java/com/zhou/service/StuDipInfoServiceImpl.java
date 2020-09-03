package com.zhou.service;

import com.zhou.dao.StuDipInfoMapper;
import com.zhou.pojo.StuDipInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuDipInfoServiceImpl implements StuDipInfoService{

    @Autowired
    @Qualifier("stuDipInfoMapper")
    private StuDipInfoMapper stuDipInfoMapper;

    public int addStudentDipInfo(StuDipInfo stuDipInfo) {
        return stuDipInfoMapper.addStudentDipInfo(stuDipInfo);
    }

    public List<StuDipInfo> queryStudentDipInfoById(int id) {
        return stuDipInfoMapper.queryStudentDipInfoById(id);
    }

    public List<StuDipInfo> queryStudentDipInfoByName(String name) {
        return stuDipInfoMapper.queryStudentDipInfoByName(name);
    }

    public int updateStudentDipInfo(StuDipInfo stuDipInfo) {
        return stuDipInfoMapper.updateStudentDipInfo(stuDipInfo);
    }

    public int deleteStudentDipInfoById(int id) {
        return stuDipInfoMapper.deleteStudentDipInfoById(id);
    }

    public List<StuDipInfo> queryAllStudentDipInfo() {
        return stuDipInfoMapper.queryAllStudentDipInfo();
    }
}
