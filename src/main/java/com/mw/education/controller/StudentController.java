package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.StudentAndClass;
import com.mw.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<StudentAndClass> pageInfo = studentService.selectAll(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        StudentAndClass studentAndClass = studentService.selectById(id);
        if (studentAndClass != null) {
            return AjaxResult.success().data(studentAndClass);
        } else {
            return AjaxResult.error().msg("没有找到对应的学生信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        boolean isSuccess = studentService.deleteById(id);
        if (isSuccess) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应学生");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Student student) {
        boolean isSuccess = studentService.edit(student);
        if (isSuccess) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应学生");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody Student student) {
        boolean isSuccess = studentService.add(student);
        if (isSuccess) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
}