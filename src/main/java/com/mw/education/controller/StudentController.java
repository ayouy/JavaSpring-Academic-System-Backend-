package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.StudentMapper;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.StudentAndClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentAndClass> list = studentMapper.getAllStudentAndClass();
        PageInfo<StudentAndClass> pageInfo = new PageInfo<>(list);

        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        StudentAndClass studentAndClass = studentMapper.getStudentAndClassByStudentId(id);
        if (studentAndClass != null) {
            return AjaxResult.success().data(studentAndClass);
        } else {
            return AjaxResult.error().msg("没有找到对应的学生信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        int n = studentMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应学生");
        }
    }

    private boolean isStudentFullyPopulated(Student student) {
        return student.getName() != null && student.getCode() != null &&
                student.getPassword() != null && student.getClassId() != null &&
                student.getRemark() != null;
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Student student) {
        int n = isStudentFullyPopulated(student) ? studentMapper.updateByPrimaryKey(student) :
                studentMapper.updateByPrimaryKeySelective(student);
        if (n > 0) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应学生");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody Student student) {
        int n = studentMapper.insertSelective(student);
        if (n > 0) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
}