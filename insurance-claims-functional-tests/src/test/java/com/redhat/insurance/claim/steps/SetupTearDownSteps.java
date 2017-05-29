package com.redhat.insurance.claim.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import com.redhat.insurance.claim.context.InsuranceClaimCucumberContext;

import cucumber.api.Scenario;

public class SetupTearDownSteps {

    private InsuranceClaimCucumberContext context;

    public SetupTearDownSteps( InsuranceClaimCucumberContext context ) {
        this.context = context;
    }

    @Before
    public void beforeScenario( Scenario scenario ) {
        context.reset();
        context.setScenario( scenario.getName() );
    }

    @After
    public void afterScenario() {
        context.reset();
    }

}
