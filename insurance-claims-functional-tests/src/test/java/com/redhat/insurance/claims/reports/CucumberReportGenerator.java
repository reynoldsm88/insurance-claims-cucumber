package com.redhat.insurance.claims.reports;

import java.io.File;
import java.util.Arrays;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class CucumberReportGenerator {

    private String[] jsonFiles;

    public CucumberReportGenerator( String... files ) {
        this.jsonFiles = files;
    }

    public void generateReports() {
        System.out.println( System.getProperty( "user.dir" ) );
        File reportOutputDirectory = new File( "target" );

        String buildNumber = "1";
        String projectName = "commonwealth-resolve";
        boolean runWithJenkins = false;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration( reportOutputDirectory, projectName );
        // optional configuration
        configuration.setParallelTesting( parallelTesting );
        configuration.setRunWithJenkins( runWithJenkins );
        configuration.setBuildNumber( buildNumber );
        // addidtional metadata presented on main page
        configuration.addClassifications( "Platform", "N/A" );
        configuration.addClassifications( "Browser", "Chrome" );

        ReportBuilder reportBuilder = new ReportBuilder( Arrays.asList( jsonFiles ), configuration );
        Reportable result = reportBuilder.generateReports();
    }

}
