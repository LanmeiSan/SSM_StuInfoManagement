package com.zhou.controller;

import com.zhou.pojo.AdminUser;
import com.zhou.pojo.StuBaseInfo;
import com.zhou.pojo.TeacherUser;
import com.zhou.service.AdminUserService;
import com.zhou.service.StuBaseInfoService;
import com.zhou.service.TeacherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/login")
public class AdminUserController {

    @Autowired
    @Qualifier("adminUserServiceImpl")
    private AdminUserService adminUserService;

    @Autowired
    @Qualifier("teacherUserServiceImpl")
    private TeacherUserService teacherUserService;

    @Autowired
    @Qualifier("stuBaseInfoServiceImpl")
    private StuBaseInfoService stuBaseInfoService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "adminLogin";
    }

    @RequestMapping("/adminLogin")
    public String login(@RequestParam(value = "adminName", required = false) String adminName, @RequestParam(value = "adminPwd") String adminPwd, Model model, HttpServletRequest request, HttpServletResponse response) {
        String optRadio = request.getParameter("optRadio");
        if (optRadio.equals("1")) {
            try {
                AdminUser adminUser1 = adminUserService.queryUser(adminName);
                if (adminUser1.getAdminName().equals(adminName) && adminUser1.getAdminPwd().equals(adminPwd)) {
                    model.addAttribute("name", adminUser1.getAdminName());
                    model.addAttribute("lim", adminUser1.getLim());
                    return "redirect:/student/allStudent";//重定向allBook请求
                } else {
                    model.addAttribute("error_login", "用户名或密码错误");
                    return "adminLogin";
                }
            } catch (Exception e) {
                model.addAttribute("error_login", "用户名或密码错误");
                return "adminLogin";
            }
        } else if (optRadio.equals("0")) {
            try {
                TeacherUser teacherUser = teacherUserService.queryUser(adminName);
                if (teacherUser.getTeaName().equals(adminName) && teacherUser.getTeaPwd().equals(adminPwd)) {
                    model.addAttribute("name", teacherUser.getTeaName());
                    model.addAttribute("lim", teacherUser.getLim());
                    return "redirect:/student/allStudent";//重定向allBook请求
                } else {
                    model.addAttribute("error_login", "用户名或密码错误");
                    return "adminLogin";
                }
            } catch (Exception e) {
                model.addAttribute("error_login", "用户名或密码错误");
                return "adminLogin";
            }
        } else if (optRadio.equals("-1")) {
            try {
                List<StuBaseInfo> stuBaseInfos = stuBaseInfoService.queryStudentById(Integer.parseInt(adminPwd));
                StuBaseInfo stuBaseInfo = stuBaseInfos.get(0);
                if (String.valueOf(stuBaseInfo.getStuId()).equals(adminPwd) && stuBaseInfo.getStuName().equals(adminName)) {
                    model.addAttribute("id", stuBaseInfo.getStuId());
                    model.addAttribute("name", stuBaseInfo.getStuName());
                    model.addAttribute("lim", stuBaseInfo.getLim());
                    return "redirect:/student/allStudent";//重定向allBook请求
                } else {
                    model.addAttribute("error_login", "用户名或密码错误");
                    return "adminLogin";
                }
            } catch (Exception e) {
                model.addAttribute("error_login", "用户名或密码错误");
                return "adminLogin";
            }
        }
        return "adminLogin";
    }


    @RequestMapping("/toa")
    public String a() {
        return "allStudent";
    }
}
