package com.zhou.dao;

import com.zhou.pojo.TeacherUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherUserMapper {

    @Select("select * from  student_info.teacher_user where `teaName`=#{adminName}")
    TeacherUser queryUser(@Param("adminName") String teaName);
}
