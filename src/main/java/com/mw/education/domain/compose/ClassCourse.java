package com.mw.education.domain.compose;

import lombok.Data;

/**
 * 课程与班级关联表
 */
@Data
public class ClassCourse {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer classId;
    private Integer courseId;
}