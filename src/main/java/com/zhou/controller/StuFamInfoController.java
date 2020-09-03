package com.zhou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.pojo.StuBaseInfo;
import com.zhou.pojo.StuDipInfo;
import com.zhou.pojo.StuFamInfo;
import com.zhou.service.StuFamInfoService;
import com.zhou.utils.StuInfoToExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/studentFamilyInfo")
public class StuFamInfoController {

    @Autowired
    @Qualifier("stuFamInfoServiceImpl")
    private StuFamInfoService stuFamInfoService;

    private StuInfoToExcel stuInfoToExcel;


    @RequestMapping("/allStudentFamilyInfo")
    public String allStudentFamilyInfo(Model model, @RequestParam(value = "pg", defaultValue = "1") Integer pg, HttpServletRequest request) {
        PageHelper.startPage(pg, 5);
        String lim = request.getParameter("lim");
        List<StuFamInfo> list = null;
        if (lim.equals("-1")) {
            int id = Integer.parseInt(request.getParameter("id"));
            list = stuFamInfoService.queryStudentFamInfoById(id);
        } else if (lim.equals("0") || lim.equals("1")) {
            list = stuFamInfoService.queryAllStudentFamInfo();
        }
        if (list.size() == 0) {
            model.addAttribute("error_Student", "信息为空");
        } else {
            PageInfo<StuFamInfo> pageInfo = new PageInfo<StuFamInfo>(list);
            model.addAttribute("page", pageInfo);
            model.addAttribute("list", list);
        }
        return "allStudentFamilyInfo";
    }


    //添加家庭信息
    @RequestMapping("/addStudentFamInfo")
    public String addStudentFamInfo(StuFamInfo stuFamInfo, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            stuFamInfoService.addStudentFamInfo(stuFamInfo);
            model.addAttribute("error_Student", "添加成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "添加失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentFamilyInfo/allStudentFamilyInfo";
    }

    //修改家庭信息
    @RequestMapping("/updateStudentFamInfo")
    public String updateStudentFamInfo(StuFamInfo stuFamInfo, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int i = stuFamInfoService.updateStudentFamInfo(stuFamInfo);
            if (i > 0) {
                model.addAttribute("id", stuFamInfo.getStuId());
                model.addAttribute("error_Student", "修改成功");
                model.addAttribute("name", name);
                model.addAttribute("lim", lim);
            } else {
                model.addAttribute("id", stuFamInfo.getStuId());
                model.addAttribute("error_Student", "修改失败");
                model.addAttribute("name", name);
                model.addAttribute("lim", lim);
            }
        } catch (Exception e) {
            model.addAttribute("id", stuFamInfo.getStuId());
            model.addAttribute("error_Student", "修改失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentFamilyInfo/allStudentFamilyInfo";
    }

    //查一个学生家庭信息（ById
    @RequestMapping("/queryStudentFamInfo")
    public String toUpdateStudent(@RequestParam(value = "pg", defaultValue = "1") Integer pg,@RequestParam(value = "queryStuFamInfoById", required = false) String queryStuFamInfoById, Model model, HttpServletRequest request) {
        PageHelper.startPage(pg, 5);
        String optRadio = request.getParameter("optRadio");
        if (optRadio.equals("1")) {
            try {
                int id = Integer.parseInt(queryStuFamInfoById);
                List<StuFamInfo> list = stuFamInfoService.queryStudentFamInfoById(id);
                if (list.size() == 0) {
                    model.addAttribute("error_queryStudent", "查找无果");
                } else {
                    model.addAttribute("error_queryStudent", "查询成功");
                    PageInfo<StuFamInfo> pageInfo = new PageInfo<StuFamInfo>(list);
                    model.addAttribute("page", pageInfo);
                    model.addAttribute("list", list);
                }
            } catch (Exception e) {
                model.addAttribute("error_queryStudent", "查找无果");
            }
        } else if (optRadio.equals("0")) {
            try {
                List<StuFamInfo> list = stuFamInfoService.queryStudentFamInfoByName(queryStuFamInfoById);
                if (list.size() == 0) {
                    model.addAttribute("error_queryStudent", "查找无果");
                } else {
                    model.addAttribute("error_queryStudent", "查询成功");
                    PageInfo<StuFamInfo> pageInfo = new PageInfo<StuFamInfo>(list);
                    model.addAttribute("page", pageInfo);
                    model.addAttribute("list", list);
                }
            } catch (Exception e) {
                model.addAttribute("error_queryStudent", "查找无果");
            }
        }
        return "allStudentFamilyInfo";
    }

    //删除一个学生ById
    @RequestMapping("/deleteStudentFamInfo")
    public String deleteStudent(@RequestParam(value = "delStuId", required = false) String stuId, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int id = Integer.parseInt(stuId);
            stuFamInfoService.deleteStudentInfoById(id);
            model.addAttribute("error_Student", "删除成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "删除失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentFamilyInfo/allStudentFamilyInfo";
    }


    @RequestMapping("/exToExcel")
    public void exToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<StuFamInfo> list = stuFamInfoService.queryAllStudentFamInfo();
        stuInfoToExcel = new StuInfoToExcel();
        HSSFWorkbook wb = stuInfoToExcel.exStuFamInfoToExcel(list);
        String fileName = "学生家庭信息.xls";
        fileName = new String(fileName.getBytes("gb2312"),"ISO-8859-1");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
