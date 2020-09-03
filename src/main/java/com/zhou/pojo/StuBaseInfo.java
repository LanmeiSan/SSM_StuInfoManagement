package com.zhou.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor/*有参构造*/
@NoArgsConstructor /*无参构造*/
public class StuBaseInfo {
    /*保持和数据库字段一致，防止空指正错误*/
    private int stuId;
    private String stuName;
    private String stuSex;
    private int stuAge;
    private String stuMaj;
    private int lim;

}
