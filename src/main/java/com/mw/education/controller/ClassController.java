package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.ClassAndSpeciality;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassGroupService classGroupService;

    @GetMapping("/")
    public AjaxResult getAllClasses(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<ClassGroup> pageInfo = classGroupService.getAllClasses(pageNum, pageSize);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult getClassById(@PathVariable Integer id) {
        ClassGroup classGroup = classGroupService.getClassById(id);
        if (classGroup != null) {
            return AjaxResult.success().data(classGroup);
        } else {
            return AjaxResult.error().msg("未找到对应的班级信息");
        }
    }

    @GetMapping("/classAndSpeciality")
    public AjaxResult getAllClassAndSpeciality() {
        PageInfo<ClassAndSpeciality> pageInfo = classGroupService.getAllClassAndSpeciality();
        if (pageInfo != null && pageInfo.getList() != null && !pageInfo.getList().isEmpty()) {
            return AjaxResult.success().data(pageInfo);
        } else {
            return AjaxResult.error().msg("未找到对应的班级及专业信息");
        }
    }

    @GetMapping("/{classId}/courses")
    public AjaxResult getClassCoursesByClassId(@PathVariable Integer classId) {
        List<ClassCourseAndCourse> list = classGroupService.getClassCoursesByClassId(classId);
        if (!list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应班级的课程信息");
        }
    }

    @PostMapping("/")
    public AjaxResult addClass(@RequestBody ClassGroup classGroup) {
        int n = classGroupService.addClass(classGroup);
        if (n > 0) {
            return AjaxResult.success().msg("添加班级成功").data(n);
        } else {
            return AjaxResult.error().msg("添加班级失败");
        }
    }

    @PutMapping("/")
    public AjaxResult updateClass(@RequestBody ClassGroup classGroup) {
        int n = classGroupService.updateClass(classGroup);
        if (n != 0) {
            return AjaxResult.success().msg("更新班级信息成功").data(n);
        } else {
            return AjaxResult.error().msg("更新班级信息失败");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteClassById(@PathVariable Integer id) {
        boolean flag = classGroupService.deleteClassById(id);
        if (flag) {
            return AjaxResult.success().msg("删除班级成功");
        } else {
            return AjaxResult.error().msg("删除班级失败");
        }
    }

    @GetMapping("{classId}/students")
    public AjaxResult getClassStudents(@PathVariable Integer classId,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                        @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<Student> list = classGroupService.getClassStudents(classId, pageNum, pageSize);
        if (!list.getList().isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应班级的学生信息");
        }
    }
}

