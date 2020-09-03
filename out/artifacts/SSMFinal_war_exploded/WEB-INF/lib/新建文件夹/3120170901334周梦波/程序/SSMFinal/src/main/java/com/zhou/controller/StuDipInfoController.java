package com.zhou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.pojo.StuBaseInfo;
import com.zhou.pojo.StuDipInfo;
import com.zhou.service.StuDipInfoService;
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
@RequestMapping("/studentDipInfo")
public class StuDipInfoController {

    @Autowired
    @Qualifier("stuDipInfoServiceImpl")
    private StuDipInfoService stuDipInfoService;

    private StuInfoToExcel stuInfoToExcel;

    //查询全部获奖信息
    @RequestMapping("/allStudentDipInfo")
    public String allStuDipInfo(Model model, @RequestParam(value = "pg", defaultValue = "1") Integer pg, HttpServletRequest request) {
        PageHelper.startPage(pg, 5);
        String lim = request.getParameter("lim");
        List<StuDipInfo> list = null;
        if (lim.equals("-1")) {
            int id = Integer.parseInt(request.getParameter("id"));
            list = stuDipInfoService.queryStudentDipInfoById(id);
        } else if (lim.equals("0") || lim.equals("1")) {
            list = stuDipInfoService.queryAllStudentDipInfo();
        }
        if (list.size() == 0) {
            model.addAttribute("error_Student", "信息为空");
        } else {
            PageInfo<StuDipInfo> pageInfo = new PageInfo<StuDipInfo>(list);
            model.addAttribute("page", pageInfo);
            model.addAttribute("list", list);

        }
        return "allStudentDipInfo";
    }

    //添加
    @RequestMapping("/addStudentDipInfo")
    public String addStudentFamInfo(StuDipInfo stuDipInfo, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            stuDipInfoService.addStudentDipInfo(stuDipInfo);
            model.addAttribute("error_Student", "添加成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "添加失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentDipInfo/allStudentDipInfo";
    }

    //修改
    //修改获奖信息
    @RequestMapping("/updateStudentDipInfo")
    public String updateStudentFamInfo(StuDipInfo stuDipInfo, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int i = stuDipInfoService.updateStudentDipInfo(stuDipInfo);
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
        return "redirect:/studentDipInfo/allStudentDipInfo";
    }

    //查一个学生获奖信息（ById
    @RequestMapping("/queryStudentDipInfo")
    public String toUpdateStudent(@RequestParam(value = "queryStuDipInfoById", required = false) String queryStuDipInfoById, Model model, HttpServletRequest request) {
        String optRadio = request.getParameter("optRadio");
        if (optRadio.equals("1")) {
            try {
                int id = Integer.parseInt(queryStuDipInfoById);
                List<StuDipInfo> list = stuDipInfoService.queryStudentDipInfoById(id);
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
                List<StuDipInfo> list = stuDipInfoService.queryStudentDipInfoByName(queryStuDipInfoById);
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
        return "allStudentDipInfo";
    }

    //删除一个学生ById
    @RequestMapping("/deleteStudentDipInfo")
    public String deleteStudent(@RequestParam(value = "delStuId", required = false) String stuId, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lim = request.getParameter("lim");
        try {
            int id = Integer.parseInt(stuId);
            stuDipInfoService.deleteStudentDipInfoById(id);
            model.addAttribute("error_Student", "删除成功");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        } catch (Exception e) {
            model.addAttribute("error_Student", "删除失败");
            model.addAttribute("name", name);
            model.addAttribute("lim", lim);
        }
        return "redirect:/studentDipInfo/allStudentDipInfo";
    }

    @RequestMapping("/exToExcel")
    public void exToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<StuDipInfo> list = stuDipInfoService.queryAllStudentDipInfo();
        stuInfoToExcel = new StuInfoToExcel();
        HSSFWorkbook wb = stuInfoToExcel.exStuDipInfoToExcel(list);
        String fileName = "学生获奖信息.xls";
        fileName = new String(fileName.getBytes("gb2312"),"ISO-8859-1");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
