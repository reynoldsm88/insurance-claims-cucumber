package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.util.List;

public class Policy implements Serializable {

    private static final long serialVersionUID = 9189019082755625837L;

    private String name;
    private long id;
    private long subscriberId;
    private List<Coverage> coverages;

}
