package com.redhat.insurance.claim.context;

import java.util.Collection;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.process.WorkItemHandler;

import com.redhat.insurance.claims.config.Configuration;
import com.redhat.insurnace.claims.bpm.TestProcessManager;

public class InsuranceClaimCucumberContext {

    private static final KieServices KIE_SERVICES = KieServices.Factory.get();
    // TODO - is there a better way to do this than have a static 
    private static final TestProcessManager processManager = new TestProcessManager();

    // Human task names

    private String scenario;

    private Map<String, WorkItemHandler> handlers;

    public void reset() {

        processManager.reset();
    }

    public Collection<Object> getAllFacts() {
        return null;
    }

    public Map<String, Object> getProcessVariables() {
        return null;
    }

    public void startProcess( String processName ) {
        if ( Configuration.isAuditLogEnabled() ) {
            processManager.enableAuditLog( scenario );
        }
        processManager.startProcess( processName, getProcessVariables(), handlers );
    }

    public void completeTask( String taskName, Map<String, Object> data ) {
        processManager.completeTask( taskName, data );
    }

    public Object getProcessVariable( String name ) {
        return processManager.getProcessVariable( name );
    }

    public String getScenario() {
        return this.scenario;
    }

    public void setScenario( String scenario ) {
        this.scenario = scenario;
    }
}