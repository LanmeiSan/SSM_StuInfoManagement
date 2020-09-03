package com.zhou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.pojo.StuFamInfo;
import com.zhou.pojo.StuWorInfo;
import com.zhou.service.StuWorInfoService;
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
@RequestMapping("/studentWorInfo")
public class StuWorInfoController {

    @Autowired
    @Qualifier("stuWorInfoServiceImpl")
    private StuWorInfoService stuWorInfoService;

    private StuInfoToExcel stuInfoToExcel;


    @RequestMapping("/allStudentWorInfo")
    public String allStudentFamilyInfo(Model model, @RequestParam(value = "pg", defaultValue = "1") Integer pg, HttpServletRequest request) {
        PageHelper.startPage(pg, 5);
        String lim = request.getParameter("lim");
        List<StuWorInfo> list = null;
        if (lim.equals("-1")) {
            int id = Integer.parseInt(request.getParameter("id"));
            list = stuWorInfoService.queryStudentWorInfoById(id);
        } else if (lim.equals("0") || lim.equals("1")) {
            list = stuWorInfoService.queryAllStudentWorInfo();
        }
        if (list.size() == 0) {
            model.addAttribute("error_Student", "信息为空");
        } else {
            PageInfo<StuWorInfo> pageInfo = new PageInfo<StuWorInfo>(list);
            model.addAttribute("page", pageInfo);
            model.addAttribute("list", list);
        }
        return "allStudentWorInfo";
    }


    //添加出勤信息
    @RequestMapping("/addStudentWorInfo")
    public String addStudentFamInfo(StuWorInfo stuWorInfo, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            stuWorInfoService.addStudentWorInfo(stuWorInfo);
            model.addAttribute("error_Student", "添加成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "添加失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentWorInfo/allStudentWorInfo";
    }

    //修改家庭信息
    @RequestMapping("/updateStudentWorInfo")
    public String updateStudentFamInfo(StuWorInfo stuWorInfo, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int i = stuWorInfoService.updateStudentWorInfo(stuWorInfo);
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
        return "redirect:/studentWorInfo/allStudentWorInfo";
    }

    //查一个学生家庭信息（ById
    @RequestMapping("/queryStudentWorInfo")
    public String queryStudentById(@RequestParam(value = "queryStuWorInfoById", required = false) String queryStuWorInfoById, Model model, HttpServletRequest request) {
        String optRadio = request.getParameter("optRadio");
        if (optRadio.equals("1")) {
            try {
                int id = Integer.parseInt(queryStuWorInfoById);
                List<StuWorInfo> list = stuWorInfoService.queryStudentWorInfoById(id);
                if (list.size() == 0) {
                    model.addAttribute("error_queryStudent", "查找无果");
                } else {
                    model.addAttribute("error_queryStudent", "查询成功");
                    model.addAttribute("list", list);
                }
            } catch (Exception e) {
                model.addAttribute("error_queryStudent", "查找无果");
            }
        } else if (optRadio.equals("0")) {
            try {
                List<StuWorInfo> list = stuWorInfoService.queryStudentWorInfoByName(queryStuWorInfoById);
                if (list.size() == 0) {
                    model.addAttribute("error_queryStudent", "查找无果");
                } else {
                    model.addAttribute("error_queryStudent", "查询成功");
                    model.addAttribute("list", list);
                }
            } catch (Exception e) {
                model.addAttribute("error_queryStudent", "查找无果");
            }
        }

        return "allStudentWorInfo";
    }

    //删除一个学生ById
    @RequestMapping("/deleteStudentWorInfo")
    public String deleteStudent(@RequestParam(value = "delStuId", required = false) String stuId, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int id = Integer.parseInt(stuId);
            stuWorInfoService.deleteStudentWorInfoById(id);
            model.addAttribute("error_Student", "删除成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "删除失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentWorInfo/allStudentWorInfo";
    }


    @RequestMapping("/exToExcel")
    public void exToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<StuWorInfo> list = stuWorInfoService.queryAllStudentWorInfo();
        stuInfoToExcel = new StuInfoToExcel();
        HSSFWorkbook wb = stuInfoToExcel.exStuWorInfoToExcel(list);
        String fileName = "学生出勤信息.xls";
        fileName = new String(fileName.getBytes("gb2312"),"ISO-8859-1");
        response.setContentType("application/vnd.ms-excel;;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
