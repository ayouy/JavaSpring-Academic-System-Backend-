package com.mw.education.domain.compose;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程与班级关联表
 */
@Data
public class ClassCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer classId;
    private Integer courseId;
}