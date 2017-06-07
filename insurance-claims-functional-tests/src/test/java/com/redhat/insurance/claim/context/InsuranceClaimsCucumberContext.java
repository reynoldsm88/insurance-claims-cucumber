package com.redhat.insurance.claim.context;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItemHandler;

import com.redhat.insurance.claims.config.Configuration;
import com.redhat.insurnace.claims.bpm.TestProcessManager;

public class InsuranceClaimsCucumberContext {

    // TODO - is there a better way to do this than have a static 
    private static final TestProcessManager processManager = new TestProcessManager();

    // Human task names

    private String scenario;

    public void startProcess( String processName ) {
        if ( Configuration.isAuditLogEnabled() ) {
            processManager.enableAuditLog( scenario );
        }
        processManager.startProcess( processName, createProcessVariables(), createTestWorkItemHandlers() );
    }

    public void completeTask( String taskName, Map<String, Object> data ) {
        processManager.completeTask( taskName, data );
    }

    public void reset() {
        this.scenario = null;
        processManager.reset();
    }

    public Object getProcessVariable( String name ) {
        return processManager.getProcessVariable( name );
    }
    
    private Collection<Object> getAllFacts() {
        return Collections.EMPTY_LIST;
    }

    private Map<String, Object> createProcessVariables() {
        Map<String, Object> variables = new HashMap<String, Object>();
        return variables;
    }

    private Map<String, WorkItemHandler> createTestWorkItemHandlers() {
        Map<String, WorkItemHandler> handlers = new HashMap<String, WorkItemHandler>();
        return handlers;
    }

    public String getScenario() {
        return this.scenario;
    }

    public void setScenario( String scenario ) {
        this.scenario = scenario;
    }
}