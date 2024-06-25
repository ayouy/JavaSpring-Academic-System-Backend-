package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.ClassAndSpeciality;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassGroupService {
    PageInfo<ClassGroup> getAllClasses(int pageNum, int pageSize);
    ClassGroup getClassById(Integer id);
    PageInfo<ClassAndSpeciality> getAllClassAndSpeciality(int pageNum, int pageSize);

    List<ClassCourseAndCourse> getClassCoursesByClassId(Integer classId);
    PageInfo<Student> getClassStudents(Integer classId, int pageNum, int pageSize);
    int addClass(ClassGroup classGroup);
    int updateClass(ClassGroup classGroup);
    boolean deleteClassById(Integer id);
}