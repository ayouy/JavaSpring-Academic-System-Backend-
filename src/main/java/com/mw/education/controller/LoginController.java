package com.mw.education.controller;

//import com.mw.education.dao.DeanMapper;
//import com.mw.education.dao.StudentMapper;
import com.mw.education.dao.DeanMapper;
import com.mw.education.dao.StudentMapper;
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
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DeanMapper deanMapper;


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
                int deanCount = deanMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
                if(deanCount == 0) {
                    return AjaxResult.error().code(401).msg("check code and password failed");
                }
                else {
                    session.setAttribute("user-code", loginData.getCode());
                    session.setAttribute("user-type", loginData.getType());
                    return AjaxResult.success().msg("login success");
                }
            case "teacher":
                int teacherCount = teacherMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
                if(teacherCount == 0) {
                    return AjaxResult.error().code(401).msg("check code and password failed");
                }
                else {
                    session.setAttribute("user-code", loginData.getCode());
                    session.setAttribute("user-type", loginData.getType());
                    return AjaxResult.success().msg("login success");
                }
            case "student":
                int studentCount = studentMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
                if(studentCount == 0) {
                    return AjaxResult.error().code(401).msg("check code and password failed");
                    }
                else {
                    session.setAttribute("user-code", loginData.getCode());
                    session.setAttribute("user-type",loginData.getType());
                    return AjaxResult.success().msg("login success");
                }
            default:
                return AjaxResult.error().code(401).msg("check type failed");
        }
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
