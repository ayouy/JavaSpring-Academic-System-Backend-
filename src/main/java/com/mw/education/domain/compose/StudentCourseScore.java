package com.mw.education.domain.compose;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 学生课程成绩表
 */
@Data
public class StudentCourseScore implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private BigDecimal score;
}