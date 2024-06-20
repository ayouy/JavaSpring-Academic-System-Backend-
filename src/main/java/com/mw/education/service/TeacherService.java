package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.controller.AjaxResult;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import com.mw.education.domain.joined_entity.TeacherCourseAndCourse;

import java.util.List;

public interface TeacherService {
    List<TeacherCourseAndCourse> getAllTeacherCourseAndCourse();
    TeacherCourseAndCourse getTeacherCourseAndCourseByTeacherCourseId(Integer id);
    List<TeacherCourseAndCourse> getTeacherCourseAndCourseByTeacherId(Integer teacherId);
    int deleteTeacherCourseByTeacherIdAndCourseId(Integer id,Integer courseId);
    int addTeacherCourse(TeacherCourse teacherCourse);
    int updateTeacherCourse(TeacherCourse teacherCourse);

    PageInfo<TeacherAndCollege> selectAll(int pageSize, int pageNum);
    TeacherAndCollege selectById(int id);
    AjaxResult deleteById(int id);
    AjaxResult edit(Teacher teacher);
    AjaxResult add(Teacher teacher);
}
