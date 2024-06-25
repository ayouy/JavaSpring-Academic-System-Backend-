package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.ClassStudents;
import com.mw.education.domain.joined_entity.SpecialityAndClassStudents;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import com.mw.education.domain.joined_entity.TeacherCourseAndCourse;
import com.mw.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<TeacherAndCollege> pageInfo = teacherService.selectAll(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{code}")
    public AjaxResult selectById(@PathVariable String code) {
        TeacherAndCollege item = teacherService.selectByTeacherCode(code);
        if (item != null) {
            return AjaxResult.success().data(item);
        } else {
            return AjaxResult.error().msg("没有找到对应的教师信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        return teacherService.deleteById(id);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher) {
        return teacherService.edit(teacher);
    }

    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher) {
        return teacherService.add(teacher);
    }
    @GetMapping("/courses")
    public AjaxResult getAllTeacherCourses() {
        List<TeacherCourseAndCourse> courses = teacherService.getAllTeacherCourseAndCourse();
        return AjaxResult.success().data(courses);
    }


    @GetMapping("/courses/{id}")
    public AjaxResult getCourseById(@PathVariable Integer id) {
        TeacherCourseAndCourse course = teacherService.getTeacherCourseAndCourseByTeacherCourseId(id);
        if (course == null) {
            return AjaxResult.error().msg("没有找到对应的教师课程信息");
        }
        return AjaxResult.success().data(course);
    }

    @GetMapping("/{code}/courses")
    public AjaxResult getTeacherCoursesByTeacherId(@PathVariable String code) {
        List<TeacherCourseAndCourse> courses = teacherService.getTeacherCourseAndCourseByTeacherCode(code);
        if (courses == null || courses.size() == 0) {
            return AjaxResult.error().msg("没有找到对应的教师课程信息");
        }
        return AjaxResult.success().data(courses);
    }


    @PostMapping("/courses")
    public AjaxResult addNewCourse(@RequestBody TeacherCourse course) {
        int result = teacherService.addTeacherCourse(course);
        if (result > 0) {
            return AjaxResult.success().msg("课程添加成功");
        }
        return AjaxResult.error().msg("课程添加失败");
    }

    @PutMapping("/courses")
    public AjaxResult updateCourse(@RequestBody TeacherCourse course) {
        int result = teacherService.updateTeacherCourse(course);
        if (result > 0) {
            return AjaxResult.success().msg("课程更新成功");
        }
        return AjaxResult.error().msg("课程更新失败");
    }

    @DeleteMapping("/{code}&{courseId}/courses")
    public AjaxResult deleteTeacherCourseById(@PathVariable String code ,@PathVariable Integer courseId) {
        int result = teacherService.deleteTeacherCourseByTeacherCodeAndCourseId(code, courseId);
        if (result > 0) {
            return AjaxResult.success().msg("课程删除成功");
        }
        return AjaxResult.error().msg("课程删除失败");
    }

    @GetMapping("/{code}/college-classes")
    public AjaxResult selectCollegeClasses(@PathVariable String code,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                           @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<SpecialityAndClassStudents> pageInfo = teacherService.getAllTeacherSpecialityAndClassStudents(code, pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{code}/college-teachers")
    public AjaxResult selectCollegeTeachers(@PathVariable String code,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<TeacherAndCollege> pageInfo = teacherService.getAllTeacherAndCollegeByTeacherCode(code, pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }
}