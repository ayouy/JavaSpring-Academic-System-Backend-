package com.mw.education.controller;

//import com.mw.education.dao.DeanMapper;
//import com.mw.education.dao.StudentMapper;
import com.mw.education.dao.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private TeacherMapper teacherMapper;


    public static class LoginData {
        private String code;
        private String password;
        private String type;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @RequestMapping(path = "/login", method= { RequestMethod.GET, RequestMethod.POST })
    AjaxResult login(@RequestBody LoginData loginData, HttpSession session) {
        switch (loginData.getType()){
            case "dean":
                int deanCount = teacherMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
                if(deanCount == 0) {
                    return AjaxResult.error().code(401).msg("check code and password failed");
                }
                else {
                    session.setAttribute("user-code", "code");
                    session.setAttribute("user-type", "dean");
                    return AjaxResult.success().msg("login success");
                }
            case "teacher":
                int teacherCount = teacherMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
                if(teacherCount == 0) {
                    return AjaxResult.error().code(401).msg("check code and password failed");
                }
                else {
                    session.setAttribute("user-code", "code");
                    session.setAttribute("user-type", "teacher");
                    return AjaxResult.success().msg("login success");
                }
            case "student":
                int studentCount = teacherMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
                if(studentCount == 0) {
                    return AjaxResult.error().code(401).msg("check code and password failed");
                    }
                else {
                    session.setAttribute("user-code", "code");
                    session.setAttribute("user-type","student");
                    return AjaxResult.success().msg("login success");
                }
            default:
                return AjaxResult.error().code(401).msg("check type failed");
        }


        // 教务、教师、学生根据type来区分，依工号/学号来登录
        /*
        if(loginData.getType().equals("dean")) {
            int n = deanMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
            if(n == 0) {
                return AjaxResult.error().code(401).msg("check code and password failed");
            }
            else {
                // Tomcat的内置session(简单应用场合)记录数据
                session.setAttribute("user-code", "code");
                session.setAttribute("user-type", "dean");
                return AjaxResult.success().msg("login success");
            }
        }
        else if(loginData.getType().equals("teacher")) {
            int n = teacherMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
            if(n == 0) {
                return AjaxResult.error().code(401).msg("check code and password failed");
            }
            else {
                session.setAttribute("user-code", "code");
                session.setAttribute("user-type", "teacher");
                return AjaxResult.success().msg("login success");
            }
        }
        else if(loginData.getType().equals("student")) {
            int n = studentMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
            if(n == 0) {
                return AjaxResult.error().code(401).msg("check code and password failed");
            }
            else {
                session.setAttribute("user-code", "code");
                session.setAttribute("user-type", "student");
                return AjaxResult.success().msg("login success");
            }
        }
        else {
            return AjaxResult.error().code(400).msg("check login type");
        }
         */
    }

    @GetMapping("/logout")
    AjaxResult logout(HttpSession session) {
        // 将无法再取出session attribute: user-code, user-type
        session.invalidate();
        return AjaxResult.success().msg("logout success");
    }

    @GetMapping("/login-info")
    AjaxResult loginInfo(HttpSession session) {
        Map map = new HashMap<>();
        map.put("user-code", session.getAttribute("user-code"));
        map.put("user-type", session.getAttribute("user-type"));
        return AjaxResult.success().msg("login info, may be null").data(map);
    }
}
