package com.mw.education.domain.compose;

import java.util.Date;
import lombok.Data;

/**
 * 学期表
 */
@Data
public class Term {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 学期名称，例如2020-2021学年秋季学期
     */
    private String name;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;
}