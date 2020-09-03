package com.zhou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuFamInfo {
    private int stuId;
    private String stuFamName;
    private String stuFamAdd;
    private int stuFamNum;
    private String stuFamTel;
}
