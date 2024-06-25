package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Course;
import com.mw.education.domain.joined_entity.CourseAndTerm;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<Course> pageInfo = courseService.selectAll(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/course-with-term")
    public AjaxResult getAllCourseAndTerm(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<CourseAndTerm> pageInfo = courseService.getAllCourseAndTerm(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }
    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        Course course = courseService.selectById(id);
        if (course != null) {
            return AjaxResult.success().data(course);
        } else {
            return AjaxResult.error().msg("没有找到对应的课程信息");
        }
    }

    @GetMapping("/courseAndTerm/{id}")
    public AjaxResult getCourseAndTermByCourseId(@PathVariable int id) {
        CourseAndTerm courseAndTerm = courseService.getCourseAndTermByCourseId(id);
        if (courseAndTerm != null) {
            return AjaxResult.success().data(courseAndTerm);
        } else {
            return AjaxResult.error().msg("没有找到对应的课程与学期信息");
        }
    }

    @GetMapping("/allClassCourseAndCourse")
    public AjaxResult getAllClassCourseAndCourse() {
        List<ClassCourseAndCourse> list = courseService.getAllClassCourseAndCourse();
        return AjaxResult.success().data(list);
    }

    @GetMapping("/classCourseAndCourse/{id}")
    public AjaxResult getClassCourseAndCourseByCourseId(@PathVariable int id) {
        ClassCourseAndCourse classCourseAndCourse = courseService.getClassCourseAndCourseByCourseId(id);
        if (classCourseAndCourse != null) {
            return AjaxResult.success().data(classCourseAndCourse);
        } else {
            return AjaxResult.error().msg("没有找到对应的班级课程与课程信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        boolean isSuccess = courseService.deleteById(id);
        if (isSuccess) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应课程");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Course course) {
        boolean isSuccess = courseService.edit(course);
        if (isSuccess) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应课程");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody Course course) {
        boolean isSuccess = courseService.add(course);
        if (isSuccess) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }

    @PostMapping("/addCourseAndTeacher/{code}")
    public AjaxResult addCourseAndTeacher(@PathVariable String code, @RequestBody Course course) {
        int affectRow = courseService.addCourseAndTeacher(code, course);
        if (affectRow > 0) {
            return AjaxResult.success().msg("教师添加课程成功");
        } else {
            return AjaxResult.error().msg("教师添加课程失败");
        }
    }

}