package com.redhat.insurance.claims.steps;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.common.InternalAgenda;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import com.redhat.insurance.claim.context.InsuranceClaimsCucumberContext;

import cucumber.api.java.en.When;

public class ProcessSteps {

    private static final KieContainer KCONTAINER = KieServices.Factory.get().newKieClasspathContainer();
    private Map<String, String> processNameMappings;
    private InsuranceClaimsCucumberContext context;

    public ProcessSteps( InsuranceClaimsCucumberContext context ) {
        this.context = context;
        processNameMappings = new HashMap<String, String>();
        processNameMappings.put( "Report an Incident", "incident-report-intake" );

    }

    @When( "^I choose to \"([^\"]*)\"$" )
    public void i_choose_to( String processName ) throws Throwable {
        context.startProcess( "incident-report-intake" );
    }

    @When( "^I execute the \"([^\"]*)\" rules$" )
    public void i_execute_the_rules( String ruleFlowGroup ) throws Throwable {
        KieSession ksession = KCONTAINER.newKieSession( "commonwealth-resolve-ksession" );
        KieRuntimeLogger audit = KieServices.Factory.get().getLoggers().newFileLogger( ksession, "audit" );
        Agenda agenda = ksession.getAgenda();
        ( (InternalAgenda) agenda ).activateRuleFlowGroup( ruleFlowGroup );
        //        context.getAllFacts().forEach( ksession::insert );
        ksession.fireAllRules();
        audit.close();
        ksession.dispose();
    }

}
