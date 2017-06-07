package com.redhat.insurance.claims.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;
import com.redhat.insurance.claims.model.Incident;
import com.redhat.insurance.claims.model.Incident.Type;
import com.redhat.insurance.claims.utils.DateTimeUtils;

import cucumber.api.java.en.When;

public class IncidentSteps {

    private InsuranceClaimsCucumberContext context;

    public IncidentSteps( InsuranceClaimsCucumberContext context ) {
        super();
        this.context = context;
    }

    @When( "^I report the following Incident:$" )
    public void i_report_the_following_Incident( List<Map<String, String>> rows ) throws Throwable {
        //@formatter:off
        Incident incident = createIncident( rows.get( 0 ) );
        Map<String, Object> data = new HashMap<String, Object>() {{ put( "incident", incident ); } };
        context.completeTask( "Report Incident", data );
        //@formatter:on
    }

    private Incident createIncident( Map<String, String> row ) {
        Incident i = new Incident();
        i.setSubscriberId( Long.parseLong( row.get( "Subscriber Id" ) ) );
        i.setType( Type.valueOf( row.get( "Type" ) ) );
        i.setDescription( row.get( "Description" ) );
        i.setDateOccurred( DateTimeUtils.dayMonthYear( row.get( "Date Occurred" ) ) );
        return i;
    }
}
