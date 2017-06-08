package com.redhat.insurance.claims.steps;

import java.util.List;
import java.util.Map;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;
import com.redhat.insurance.claims.model.Policy;
import com.redhat.insurance.claims.utils.DateTimeUtils;

import cucumber.api.java.en.Given;

public class PolicySteps {

    private InsuranceClaimsCucumberContext context;

    public PolicySteps( InsuranceClaimsCucumberContext context ) {
        super();
        this.context = context;
    }

    @Given( "^I have the following Policy:$" )
    public void i_have_the_following_Policy( List<Map<String, String>> rows ) throws Throwable {
        Policy policy = createPolicy( rows.get( 0 ) );
        context.setPolicy( policy );
    }

    private Policy createPolicy( Map<String, String> row ) {
        Policy policy = new Policy();
        policy.setId( Long.parseLong( row.get( "Id" ) ) );
        policy.setSubscriberId( Long.parseLong( row.get( "Subscriber Id" ) ) );
        policy.setName( row.get( "Name" ) );
        return policy;
    }

}