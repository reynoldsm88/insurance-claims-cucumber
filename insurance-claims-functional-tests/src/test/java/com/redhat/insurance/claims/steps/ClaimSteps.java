package com.redhat.insurance.claims.steps;

import java.util.List;
import java.util.Map;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;
import com.redhat.insurance.claims.model.Claim;

import cucumber.api.java.en.Then;

public class ClaimSteps {

    private InsuranceClaimsCucumberContext context;

    public ClaimSteps( InsuranceClaimsCucumberContext context ) {
        super();
        this.context = context;
    }

    @Then( "^I expect the following Claim to be created:$" )
    public void i_expect_the_following_Claim_to_be_created( List<Map<String, String>> rows ) throws Throwable {
        Claim claim = createClaim( rows.get( 0 ) );
    }

    private Claim createClaim( Map<String, String> row ) {
        return null;
    }

}
