package com.redhat.insurance.claims.steps;

import java.util.List;
import java.util.Map;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;
import com.redhat.insurance.claims.model.Subscriber;

import cucumber.api.java.en.Given;

public class SubscriberSteps {

    private InsuranceClaimsCucumberContext context;

    public SubscriberSteps( InsuranceClaimsCucumberContext context ) {
        this.context = context;
    }

    @Given( "^I am the following Subscriber:$" )
    public void i_am_the_following_Subscriber( List<Map<String, String>> rows ) throws Throwable {
        createSubscriber( rows.get( 0 ) );
    }

    private Subscriber createSubscriber( Map<String, String> row ) {
        return null;
    }
}
