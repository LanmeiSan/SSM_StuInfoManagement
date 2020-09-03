package com.zhou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.pojo.StuBaseInfo;
import com.zhou.service.StuBaseInfoService;
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
@RequestMapping("/student")
public class StuBaseInfoController {

    @Autowired
    @Qualifier("stuBaseInfoServiceImpl")
    private StuBaseInfoService stuBaseInfoService;

//    @RequestMapping("/toAddStudent")
//    public String toAddStudent(){
//        return "redirect:/student/addStudent";
//    }
    private StuInfoToExcel stuInfoToExcel;

    //增加一个学生
    @RequestMapping("/addStudent")
    public String addStudent(StuBaseInfo stuBaseInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            stuBaseInfoService.addStudent(stuBaseInfo);
            model.addAttribute("error_Student", "添加成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "添加失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/student/allStudent";
    }

//    @RequestMapping("/toAllStudent")
//    public String toAllStudent(){
//        return "allStudent";
//    }

    //查询全部学生
    @RequestMapping("/allStudent")
    public String list(Model model, @RequestParam(value = "pg", defaultValue = "1") Integer pg, HttpServletRequest request) {
        PageHelper.startPage(pg, 5);
        String lim = request.getParameter("lim");
        List<StuBaseInfo> list = null;
        if (lim.equals("-1")) {
            int id = Integer.parseInt(request.getParameter("id"));
            list = stuBaseInfoService.queryStudentById(id);
        } else if (lim.equals("0") || lim.equals("1")) {
            list = stuBaseInfoService.queryAllStudent();
        }
        if (list.size() == 0) {
            model.addAttribute("error_Student", "信息为空");
        } else {
            PageInfo<StuBaseInfo> pageInfo = new PageInfo<StuBaseInfo>(list);
            model.addAttribute("page", pageInfo);
            model.addAttribute("list", list);
        }
        return "allStudent";
    }

    //修改学生信息
    @RequestMapping("/updateStudent")
    public String updateStudent(StuBaseInfo stuBaseInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int i = stuBaseInfoService.updateStudent(stuBaseInfo);
            if (i > 0) {
                model.addAttribute("error_Student", "修改成功");
                model.addAttribute("name", name);
                model.addAttribute("lim", lim);
            } else {
                model.addAttribute("error_Student", "修改失败");
                model.addAttribute("name", name);
                model.addAttribute("lim", lim);
            }
        } catch (Exception e) {
            model.addAttribute("error_Student", "修改失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/student/allStudent";
    }

    //查一个学生（ById或name
    @RequestMapping("/queryStudent")
    public String toUpdateStudent(@RequestParam(value = "queryStuById", required = false) String queryStuById, Model model, HttpServletRequest request) {
        String optRadio = request.getParameter("optRadio");
        if (optRadio.equals("1")) {
            try {
                int id = Integer.parseInt(queryStuById);
                List<StuBaseInfo> list = stuBaseInfoService.queryStudentById(id);
                if (list.size() == 0) {
                    model.addAttribute("error_queryStudent", "查询无果");
                } else {
                    model.addAttribute("error_queryStudent", "查询成功");
                    model.addAttribute("list", list);
                }
            } catch (Exception e) {
                model.addAttribute("error_queryStudent", "查找无果");
            }
        } else if (optRadio.equals("0")) {
            try {
                List<StuBaseInfo> list = stuBaseInfoService.queryStudentByName(queryStuById);
                if (list.size() == 0) {
                    model.addAttribute("error_queryStudent", "查询无果");
                } else {
                    model.addAttribute("error_queryStudent", "查询成功");
                    model.addAttribute("list", list);
                }
            } catch (Exception e) {
                model.addAttribute("error_queryStudent", "查找无果");
            }
        }
        return "allStudent";
    }

    //删除一个学生ById
    @RequestMapping("/deleteStudent")
    public String deleteStudent(@RequestParam(value = "delStuId", required = false) String stuId, Model model, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int id = Integer.parseInt(stuId);
            stuBaseInfoService.deleteStudentById(id);
            model.addAttribute("error_Student", "删除成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "删除失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/student/allStudent";
    }

    @RequestMapping("/exToExcel")
    public void exToExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<StuBaseInfo> list = stuBaseInfoService.queryAllStudent();
        stuInfoToExcel = new StuInfoToExcel();
        HSSFWorkbook wb = stuInfoToExcel.exStuBaseInfoToExcel(list);
        String fileName = "学生基本信息.xls";
        fileName = new String(fileName.getBytes("gb2312"),"ISO-8859-1");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
