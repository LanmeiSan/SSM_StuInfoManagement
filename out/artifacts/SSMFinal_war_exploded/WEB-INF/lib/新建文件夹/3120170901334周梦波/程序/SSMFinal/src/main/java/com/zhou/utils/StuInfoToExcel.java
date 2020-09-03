package com.zhou.utils;

import com.zhou.pojo.StuBaseInfo;
import com.zhou.pojo.StuDipInfo;
import com.zhou.pojo.StuFamInfo;
import com.zhou.pojo.StuWorInfo;
import org.apache.poi.hssf.usermodel.*;

import java.util.List;

public class StuInfoToExcel {

    //学生基础信息
    public HSSFWorkbook exStuBaseInfoToExcel(List<StuBaseInfo> stuBaseInfo) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("学生基本信息");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("年龄");
        row.createCell(4).setCellValue("专业");
        //导入数据
        for(int i=0;i<stuBaseInfo.size();i++){
            row=sheet.createRow(i+1);
            StuBaseInfo stuBaseInfo1 = stuBaseInfo.get(i);
            row.createCell(0).setCellValue(stuBaseInfo1.getStuId());
            row.createCell(1).setCellValue(stuBaseInfo1.getStuName());
            row.createCell(2).setCellValue(stuBaseInfo1.getStuSex());
            row.createCell(3).setCellValue(stuBaseInfo1.getStuAge());
            row.createCell(4).setCellValue(stuBaseInfo1.getStuMaj());
        }
        return wb;
    }
    //学生家庭信息
    public HSSFWorkbook exStuFamInfoToExcel(List<StuFamInfo> stuFamInfo) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("学生家庭信息");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("家庭地址");
        row.createCell(3).setCellValue("家庭人口");
        row.createCell(4).setCellValue("家庭电话");
        //导入数据
        for(int i=0;i<stuFamInfo.size();i++){
            row=sheet.createRow(i+1);
            StuFamInfo stuFamInfo1 = stuFamInfo.get(i);
            row.createCell(0).setCellValue(stuFamInfo1.getStuId());
            row.createCell(1).setCellValue(stuFamInfo1.getStuFamName());
            row.createCell(2).setCellValue(stuFamInfo1.getStuFamAdd());
            row.createCell(3).setCellValue(stuFamInfo1.getStuFamNum());
            row.createCell(4).setCellValue(stuFamInfo1.getStuFamTel());
        }
        return wb;
    }

    //学生获奖信息
    public HSSFWorkbook exStuDipInfoToExcel(List<StuDipInfo> stuDipInfo) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("学生获奖信息");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("获奖名称");
        row.createCell(3).setCellValue("获奖时间");
        row.createCell(4).setCellValue("获奖备注");
        //导入数据
        for(int i=0;i<stuDipInfo.size();i++){
            row=sheet.createRow(i+1);
            StuDipInfo stuDipInfo1 = stuDipInfo.get(i);
            row.createCell(0).setCellValue(stuDipInfo1.getStuId());
            row.createCell(1).setCellValue(stuDipInfo1.getStuDipName());
            row.createCell(2).setCellValue(stuDipInfo1.getStuDipName1());
            row.createCell(3).setCellValue(stuDipInfo1.getStuDipTim());
            row.createCell(4).setCellValue(stuDipInfo1.getStuDipPs());
        }
        return wb;
    }


    //学生缺勤信息
    public HSSFWorkbook exStuWorInfoToExcel(List<StuWorInfo> stuWorInfo) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("学生缺勤信息");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("缺勤次数");
        row.createCell(3).setCellValue("缺勤处分");
        row.createCell(4).setCellValue("缺勤备注");
        //导入数据
        for(int i=0;i<stuWorInfo.size();i++){
            row=sheet.createRow(i+1);
            StuWorInfo StuWorInfo1 = stuWorInfo.get(i);
            row.createCell(0).setCellValue(StuWorInfo1.getStuId());
            row.createCell(1).setCellValue(StuWorInfo1.getStuWorName());
            row.createCell(2).setCellValue(StuWorInfo1.getStuWorNum());
            row.createCell(3).setCellValue(StuWorInfo1.getStuWorPun());
            row.createCell(4).setCellValue(StuWorInfo1.getStuWorPs());
        }
        return wb;
    }


}
