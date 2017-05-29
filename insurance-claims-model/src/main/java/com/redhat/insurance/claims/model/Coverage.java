package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Coverage implements Serializable {

    public enum Level {
        STANDARD, PREMIUM
    }

    private static final long serialVersionUID = -6999604172528890547L;
    private long policyId;
    private String name;
    private Level level;
    private BigDecimal amount;
    private LocalDate effectiveDate;
    private LocalDate terminationDate;

}
