package com.zhou.dao;

import com.zhou.pojo.StuBaseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StuBaseInfoMapper {
    //增加一个学生
    @Insert(" insert into student_info.student_baseinfo " +
            "(`stuId`,`stuName`,`stuSex`,`stuAge`,`stuMaj`) values " +
            "(#{stuId},#{stuName},#{stuSex},#{stuAge},#{stuMaj})")
    int addStudent(StuBaseInfo stuBaseInfo);

    //删除一个学生
    @Delete("delete from student_info.student_baseinfo where `stuId`=#{stuId}")
    int deleteStudentById(@Param("stuId") int id);

    //修改
    @Update("update student_info.student_baseinfo set " +
            "stuName=#{stuName},stuSex=#{stuSex},stuAge=#{stuAge},stuMaj=#{stuMaj}  " +
            "where stuId=#{stuId}")
    int updateStudent(StuBaseInfo stuBaseInfo);

    //查询一个学生
    @Select("select * from  student_info.student_baseinfo where `stuId`=#{stuId}")
    List<StuBaseInfo> queryStudentById(@Param("stuId") int id);


    //查询一个学生
    @Select("select * from  student_info.student_baseinfo where `stuName`=#{stuName}")
    List<StuBaseInfo> queryStudentByName(@Param("stuName") String name);

    //查询全部学生
    @Select("select * from student_info.student_baseinfo")
    List<StuBaseInfo> queryAllStudent();

}
