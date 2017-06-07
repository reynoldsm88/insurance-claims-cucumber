package com.redhat.insurance.claims.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;

import cucumber.api.Scenario;

public class SetupTearDownSteps {

    private InsuranceClaimsCucumberContext context;

    public SetupTearDownSteps( InsuranceClaimsCucumberContext context ) {
        this.context = context;
    }

    @Before
    public void beforeScenario( Scenario scenario ) {
        //        context.reset();
        //        context.setScenario( scenario.getName() );
    }

    @After
    public void afterScenario() {
//        context.reset();
    }

}
