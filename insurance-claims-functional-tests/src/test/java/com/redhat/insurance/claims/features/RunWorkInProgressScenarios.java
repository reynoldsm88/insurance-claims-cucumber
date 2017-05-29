package com.redhat.insurance.claims.features;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith( Cucumber.class )
@CucumberOptions( plugin = "json:target/wip-report.json", glue = "com.redhat.insurance.claims", features = "src/test/resources/features", tags = { "@wip" } )
public class RunWorkInProgressScenarios {}
