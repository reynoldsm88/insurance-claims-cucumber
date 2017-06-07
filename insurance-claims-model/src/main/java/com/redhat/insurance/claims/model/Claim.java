package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Claim implements Serializable {

    public enum Status {
        SUBMITTED, ELIGIBILE, UNDER_REVIEW, APPROVED, DENIED
    }

    private static final long serialVersionUID = 863367936509952087L;
    private Subscriber subcriber;
    private Incident incident;
    private BigDecimal amount;
    private LocalDate dateFiled;
    private Status status;

}