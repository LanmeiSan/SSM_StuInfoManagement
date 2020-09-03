package com.zhou.service;

import com.zhou.dao.StuFamInfoMapper;
import com.zhou.pojo.StuFamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuFamInfoServiceImpl implements StuFamInfoService {

    @Autowired
    @Qualifier("stuFamInfoMapper")
    private StuFamInfoMapper stuFamInfoMapper;


    public int addStudentFamInfo(StuFamInfo stuFamInfo) {
        return stuFamInfoMapper.addStudentFamInfo(stuFamInfo);
    }

    public List<StuFamInfo> queryStudentFamInfoById(int id) {
        return stuFamInfoMapper.queryStudentFamInfoById(id);
    }

    public List<StuFamInfo> queryStudentFamInfoByName(String name) {
        return stuFamInfoMapper.queryStudentFamInfoByName(name);
    }

    public int updateStudentFamInfo(StuFamInfo stuFamInfo) {
        return stuFamInfoMapper.updateStudentFamInfo(stuFamInfo);
    }

    public int deleteStudentInfoById(int id) {
        return stuFamInfoMapper.deleteStudentFamInfoById(id);
    }

    public List<StuFamInfo> queryAllStudentFamInfo() {
        return stuFamInfoMapper.queryAllStudentFamilyInfo();
    }
}
