package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.mw.education.dao.TeacherMapper;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;

    // http://127.0.0.1:8080/teachers
    @GetMapping("/teachers")
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10" ) int pageSize,
                         @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        /*
         * 分页插件支持几种调用方式，推荐这种使用以下方式。
         * 在需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，
         * 紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页
         *
         * 可以使用PageInfo对结果进行包装，
         * PageInfo包含了非常全面的分页属性
         */
        PageHelper.startPage(pageNum, pageSize);
        List list = teacherMapper.selectAllJoinedCollege();

        AjaxResult result = AjaxResult.success().rows(list.size(), list);
        return result;
    }

    // http://127.0.0.1:8080/teachers/1
    @GetMapping("/teachers/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        TeacherAndCollege item = teacherMapper.selectByPrimaryKeyJoinedCollege(id);
        List list = new ArrayList<>();
        list.add(item);

        AjaxResult result = AjaxResult.success().rows(1, list);
        return result;
    }

    // http://127.0.0.1:8080/teachers/1
    @DeleteMapping("/teachers/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        int n = teacherMapper.deleteByPrimaryKey(id);

        AjaxResult result = AjaxResult.success().updateResult(n);
        return result;
    }

    // http://127.0.0.1:8080/teachers
    @PutMapping("/teachers")
    public AjaxResult edit(@RequestBody Teacher teacher) {
        int n = teacherMapper.updateByPrimaryKey(teacher);

        AjaxResult result = AjaxResult.success().updateResult(n);
        return result;
    }

    // http://127.0.0.1:8080/teachers
    @PostMapping("/teachers")
    public AjaxResult add(@RequestBody Teacher teacher) {
        int n = teacherMapper.insert(teacher);

        AjaxResult result = AjaxResult.success().updateResult(n);
        return result;
    }
}
