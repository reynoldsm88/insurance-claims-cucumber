package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Incident implements Serializable {

    private static final long serialVersionUID = 8558085574111931158L;
    private long subscriberId;
    private String description;
    private LocalDate dateOfOccurrence;

}