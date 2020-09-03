package com.zhou.dao;

import com.zhou.pojo.StuDipInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StuDipInfoMapper {
    //增加一条学生家庭信息
    @Insert("insert into student_info.student_dipinfo " +
            "(`stuId`,`stuDipName`,`stuDipName1`,`stuDipTim`,`stuDipPs`) values " +
            "(#{stuId},#{stuDipName},#{stuDipName1},#{stuDipTim},#{stuDipPs})")
    int addStudentDipInfo(StuDipInfo stuDipInfo );

    //删除一个学生
    @Delete("delete from student_info.student_dipinfo where stuId=#{stuId}")
    int deleteStudentDipInfoById(@Param("stuId") int id);

    //修改
    @Update("update student_info.student_dipinfo set " +
            "stuDipName=#{stuDipName},stuDipName1=#{stuDipName1},stuDipTim=#{stuDipTim},stuDipPs=#{stuDipPs}  " +
            "where stuId=#{stuId}")
    int updateStudentDipInfo(StuDipInfo stuDipInfo);

    //查询全部学生家庭信息
    @Select("select * from student_info.student_dipinfo")
    List<StuDipInfo> queryAllStudentDipInfo();

    //查询一个
    @Select("select * from  student_info.student_dipinfo where `stuId`=#{stuId}")
    List<StuDipInfo> queryStudentDipInfoById(@Param("stuId") int id);

    //查询一个
    @Select("select * from  student_info.student_dipinfo where `stuDipName`=#{stuDipName}")
    List<StuDipInfo> queryStudentDipInfoByName(@Param("stuDipName") String name);
}
