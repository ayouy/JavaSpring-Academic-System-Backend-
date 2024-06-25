package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Course;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.domain.joined_entity.StudentAndClass;
import com.mw.education.domain.joined_entity.StudentCourseScoreAndCourse;
import com.mw.education.domain.joined_entity.TeacherAndCollege;

public interface StudentService {
    PageInfo<StudentAndClass> selectAll(int pageSize, int pageNum);
    StudentAndClass selectById(int id);
    boolean deleteById(int id);
    boolean edit(Student student);
    boolean add(Student student);

    PageInfo<StudentAndClass> selectClassmates(String code ,int pageSize, int pageNum) ;
    PageInfo<TeacherAndCollege> selectCollegeTeachers(String code, int pageSize, int pageNum) ;
    PageInfo<ClassCourseAndCourse> selectClassCourses(String code, int pageSize, int pageNum);

    PageInfo<StudentCourseScoreAndCourse> selectScoreByStudentCode(String code, int pageSize, int pageNum);
}