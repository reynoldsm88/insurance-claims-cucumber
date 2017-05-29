package com.redhat.insurance.claims.reports;

import org.junit.Ignore;
import org.junit.Test;

public class GenerateCucumberReports {

    @Test
    @Ignore
    public void generateReports() {
        new CucumberReportGenerator( "target/non-received-reports.json", "target/reg-z-reports.json" ).generateReports();
    }

}