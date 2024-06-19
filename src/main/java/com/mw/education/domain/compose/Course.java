package com.mw.education.domain.compose;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 课程表
 */
@Data
public class Course implements Serializable {
    private Integer id;

    private String name;

    private String code;

    private Integer termId;

    private BigDecimal credit;

    private Integer collegeId;

    private static final long serialVersionUID = 1L;
}