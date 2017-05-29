package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Claim implements Serializable {

    public enum Status {
        SUBMITTED, ELIGIBILE_FOR_COVERAGE, UNDER_REVIEW, APPROVED, DENIED
    }

    private static final long serialVersionUID = 863367936509952087L;
    private long subscriberId;
    private long incidentId;
    private BigDecimal amount;
    private LocalDate dateFiled;
    private Status status;

}