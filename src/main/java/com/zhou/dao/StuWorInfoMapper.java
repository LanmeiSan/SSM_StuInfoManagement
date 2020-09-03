package com.zhou.dao;

import com.zhou.pojo.StuWorInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StuWorInfoMapper {
    //增加一条学生出勤信息
    @Insert("insert into student_info.student_worinfo " +
            "(`stuId`,`stuWorName`,`stuWorNum`,`stuWorPun`,`stuWorPs`) values " +
            "(#{stuId},#{stuWorName},#{stuWorNum},#{stuWorPun},#{stuWorPs})")
    int addStudentWorInfo(StuWorInfo stuWorInfo );

    //删除一个学生
    @Delete("delete from student_info.student_worinfo where stuId=#{stuId}")
    int deleteStudentWorInfoById(@Param("stuId") int id);

    //修改
    @Update("update student_info.student_worinfo set " +
            "stuWorName=#{stuWorName},stuWorNum=#{stuWorNum},stuWorPun=#{stuWorPun},stuWorPs=#{stuWorPs}  " +
            "where stuId=#{stuId}")
    int updateStudentWorInfo(StuWorInfo stuWorInfo);

    //查询全部学生出勤信息
    @Select("select * from student_info.student_worinfo")
    List<StuWorInfo> queryAllStudentWorInfo();

    //查询一个
    @Select("select * from  student_info.student_worinfo where `stuId`=#{stuId}")
    List<StuWorInfo> queryStudentWorInfoById(@Param("stuId") int id);

    //查询一个
    @Select("select * from  student_info.student_worinfo where `stuWorName`=#{stuWorName}")
    List<StuWorInfo> queryStudentWorInfoByName(@Param("stuWorName")String name);
}
