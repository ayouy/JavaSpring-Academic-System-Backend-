package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.TeacherMapper;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherAndCollege> list = teacherMapper.getAllTeacherAndCollege();
        PageInfo<TeacherAndCollege> pageInfo = new PageInfo<>(list);

        // 使用PageInfo包装查询结果，返回更多分页信息
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        TeacherAndCollege item = teacherMapper.getTeacherAndCollegeByTeacherId(id);
        if (item != null) {
            return AjaxResult.success().data(item);
        } else {
            return AjaxResult.error().msg("没有找到对应的教师信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        int n = teacherMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应教师");
        }
    }

    private boolean isTeacherFullyPopulated(Teacher teacher) {
        return teacher.getName() != null && teacher.getCode() != null &&
                teacher.getPassword() != null && teacher.getCollegeId() != null &&
                teacher.getRemark() != null;
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher) {
        int n;
        if (isTeacherFullyPopulated(teacher)) {
            n = teacherMapper.updateByPrimaryKey(teacher);
        } else {
            n = teacherMapper.updateByPrimaryKeySelective(teacher);
        }

        if (n > 0) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应教师");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher) {
        int n;
        if (isTeacherFullyPopulated(teacher)) {
            n = teacherMapper.insert(teacher);
        } else {
            n = teacherMapper.insertSelective(teacher);
        }
        if (n > 0) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
}