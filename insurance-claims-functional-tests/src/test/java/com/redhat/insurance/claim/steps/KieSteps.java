package com.redhat.insurance.claim.steps;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.common.InternalAgenda;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import com.redhat.insurance.claim.context.InsuranceClaimCucumberContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class KieSteps {

    private Map<String, String> processNameMapping;
    private InsuranceClaimCucumberContext context;
    private KieContainer KCONTAINER = KieServices.Factory.get().newKieClasspathContainer();

    boolean auditBool = false;

    public KieSteps( InsuranceClaimCucumberContext context ) {
        this.context = context;
        processNameMapping = new HashMap<String, String>();
        processNameMapping.put( "Non-Received Merchandise", "NonReceivedMerchandiseDispute" );

    }

    @Given( "^I've started the process: \"([^\"]*)\"$" )
    public void i_ve_started_the_process( String arg1 ) throws Throwable {

    }

    @When( "^I start the process: \"([^\"]*)\"$" )
    public void i_start_the_process( String processName ) throws Throwable {
        context.startProcess( processNameMapping.get( processName ) );
    }

    @When( "^I execute the \"([^\"]*)\" rules$" )
    public void i_execute_the_rules( String ruleFlowGroup ) throws Throwable {
        KieSession ksession = KCONTAINER.newKieSession( "commonwealth-resolve-ksession" );
        KieRuntimeLogger audit = KieServices.Factory.get().getLoggers().newFileLogger( ksession, "audit" );
        Agenda agenda = ksession.getAgenda();
        ( (InternalAgenda) agenda ).activateRuleFlowGroup( ruleFlowGroup );
        context.getAllFacts().forEach( ksession::insert );
        ksession.fireAllRules();
        audit.close();
        ksession.dispose();
    }

}
