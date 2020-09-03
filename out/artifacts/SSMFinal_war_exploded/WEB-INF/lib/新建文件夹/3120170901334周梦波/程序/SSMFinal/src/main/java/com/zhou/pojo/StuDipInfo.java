package com.zhou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuDipInfo {
    private int stuId;
    private String stuDipName;
    private String stuDipName1;
    private Date stuDipTim;
    private String stuDipPs;

}
