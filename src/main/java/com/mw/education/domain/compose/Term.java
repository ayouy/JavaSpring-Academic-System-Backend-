package com.mw.education.domain.compose;

import java.util.Date;
import lombok.Data;

import javax.validation.constraints.AssertTrue;

/**
 * 学期表
 */
@Data
public class Term {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;

    @AssertTrue(message = "startDate必须早于endDate")
    public boolean isStartBeforeEnd() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return startDate.before(endDate);
    }
}