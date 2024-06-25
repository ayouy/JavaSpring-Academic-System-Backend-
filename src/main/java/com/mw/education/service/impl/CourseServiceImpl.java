package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.CourseMapper;
import com.mw.education.dao.TeacherCourseMapper;
import com.mw.education.dao.TeacherMapper;
import com.mw.education.domain.compose.Course;
import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.CourseAndTerm;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public PageInfo<Course> selectAll(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Course selectById(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public CourseAndTerm getCourseAndTermByCourseId(int id) {
        return courseMapper.getCourseAndTermByCourseId(id);
    }
    @Override
    public PageInfo<CourseAndTerm> getAllCourseAndTerm(int pageSize,int pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<CourseAndTerm> list = courseMapper.getAllCourseAndTerm();
        return new PageInfo<>(list);
    }
    @Override
    public List<ClassCourseAndCourse> getAllClassCourseAndCourse() {
        return courseMapper.getAllClassCourseAndCourse();
    }

    @Override
    public ClassCourseAndCourse getClassCourseAndCourseByCourseId(int id) {
        return courseMapper.getClassCourseAndCourseByCourseId(id);
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        courseMapper.deleteTeacherCourseByCourseId(id);
        courseMapper.deleteStudentCourseScoreByCourseId(id);
        courseMapper.deleteClassCourseByCourseId(id);
        return courseMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean edit(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course) > 0;
    }

    @Override
    public boolean add(Course course) {
        return courseMapper.insertSelective(course) > 0;
    }

    @Override
    @Transactional
    public int addCourseAndTeacher(String code ,Course course) {
        courseMapper.insertSelective(course);
        int courseId = course.getId();
        int teacherId = teacherMapper.getPrimaryKey(code);
        return teacherCourseMapper.insertSelective(new TeacherCourse(teacherId, courseId));
    }
}