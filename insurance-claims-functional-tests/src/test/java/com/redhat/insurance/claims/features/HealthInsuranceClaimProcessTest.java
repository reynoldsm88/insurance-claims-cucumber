package com.redhat.insurance.claims.features;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith( Cucumber.class )
@CucumberOptions( plugin = "json:target/health-insurance-process-report.json", glue = "com.redhat.insurance.claims.steps", features = "src/test/resources/features/insurance-claims-process" )
public class HealthInsuranceClaimProcessTest {}
