package com.zhou.dao;

import com.zhou.pojo.StuFamInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StuFamInfoMapper {

    //增加一条学生家庭信息
    @Insert("insert into student_info.student_familyinfo " +
            "(`stuId`,`stuFamName`,`stuFamAdd`,`stuFamNum`,`stuFamTel`) values " +
            "(#{stuId},#{stuFamName},#{stuFamAdd},#{stuFamNum},#{stuFamTel})")
    int addStudentFamInfo(StuFamInfo stuFamInfo );

    //删除一个学生
    @Delete("delete from student_info.student_familyinfo where stuId=#{stuId}")
    int deleteStudentFamInfoById(@Param("stuId") int id);

    //修改
    @Update("update student_info.student_familyinfo set " +
            "stuFamName=#{stuFamName},stuFamAdd=#{stuFamAdd},stuFamNum=#{stuFamNum},stuFamTel=#{stuFamTel}  " +
            "where stuId=#{stuId}")
    int updateStudentFamInfo(StuFamInfo stuFamInfo);

    //查询全部学生家庭信息
    @Select("select * from student_info.student_familyinfo")
    List<StuFamInfo> queryAllStudentFamilyInfo();

    //查询一个
    @Select("select * from  student_info.student_familyinfo where `stuId`=#{stuId}")
    List<StuFamInfo> queryStudentFamInfoById(@Param("stuId") int id);

    //查询一个
    @Select("select * from  student_info.student_familyinfo where `stuFamName`=#{stuFamName}")
    List<StuFamInfo> queryStudentFamInfoByName(@Param("stuFamName") String name);
}
