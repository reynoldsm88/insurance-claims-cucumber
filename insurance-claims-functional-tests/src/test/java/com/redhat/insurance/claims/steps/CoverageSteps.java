package com.redhat.insurance.claims.steps;

import java.util.List;
import java.util.Map;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;
import com.redhat.insurance.claims.model.Coverage;

import cucumber.api.java.en.Given;

public class CoverageSteps {

    private InsuranceClaimsCucumberContext context;

    public CoverageSteps( InsuranceClaimsCucumberContext context ) {
        super();
        this.context = context;
    }

    @Given( "^the Policy has the following Coverages:$" )
    public void the_Policy_has_the_following_Coverages( List<Map<String, String>> rows ) throws Throwable {
        createCoverage( rows.get( 0 ) );
    }

    private Coverage createCoverage( Map<String, String> row ) {
        return null;
    }
}
