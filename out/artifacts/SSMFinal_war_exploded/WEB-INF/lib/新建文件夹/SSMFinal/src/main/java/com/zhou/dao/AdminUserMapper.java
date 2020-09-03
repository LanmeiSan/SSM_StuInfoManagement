package com.zhou.dao;

import com.zhou.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {
    @Select("select * from  student_info.admin_user where `adminName`=#{adminName}")
    AdminUser queryUser(@Param("adminName") String adminName);
}
