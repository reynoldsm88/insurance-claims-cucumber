package com.redhat.insurance.claims.steps;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Set<Coverage> coverages = createCoverages( rows );
        context.getPolicy().setCoverages( coverages );
    }

    private Set<Coverage> createCoverages( List<Map<String, String>> rows ) {
        Set<Coverage> coverages = new HashSet<Coverage>();
        rows.forEach( row -> coverages.add( createCoverage( row ) ) );
        return coverages;

    }

    private Coverage createCoverage( Map<String, String> row ) {
        Coverage coverage = new Coverage();
        // TODO pick up here
        return coverage;
    }
}
