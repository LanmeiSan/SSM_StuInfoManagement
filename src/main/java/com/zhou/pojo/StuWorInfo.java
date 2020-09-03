package com.zhou.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuWorInfo {
    private int stuId;
    private String stuWorName;
    private int stuWorNum;
    private String stuWorPun;
    private String stuWorPs;

}
