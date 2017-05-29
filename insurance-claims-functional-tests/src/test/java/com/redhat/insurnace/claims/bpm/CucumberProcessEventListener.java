package com.redhat.insurnace.claims.bpm;

import java.util.Map;

import org.jbpm.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.runtime.process.EventListener;

/**
 * 
 * This event will fire right before the process completes and capture the
 * process variables at the end of the process
 *
 */
public class CucumberProcessEventListener implements ProcessEventListener, EventListener {

    private Map<String, Object> processVariables;

    public Map<String, Object> getProcessVariables() {
        return this.processVariables;
    }

    @Override
    public void beforeProcessStarted( ProcessStartedEvent event ) {
    }

    @Override
    public void afterProcessStarted( ProcessStartedEvent event ) {
    }

    @Override
    public void beforeProcessCompleted( ProcessCompletedEvent event ) {
        WorkflowProcessInstanceImpl instance = (WorkflowProcessInstanceImpl) event.getProcessInstance();
        processVariables = instance.getVariables();
    }

    @Override
    public void afterProcessCompleted( ProcessCompletedEvent event ) {
    }

    @Override
    public void beforeNodeTriggered( ProcessNodeTriggeredEvent event ) {
    }

    @Override
    public void afterNodeTriggered( ProcessNodeTriggeredEvent event ) {
    }

    @Override
    public void beforeNodeLeft( ProcessNodeLeftEvent event ) {
    }

    @Override
    public void afterNodeLeft( ProcessNodeLeftEvent event ) {
    }

    @Override
    public void beforeVariableChanged( ProcessVariableChangedEvent event ) {
    }

    @Override
    public void afterVariableChanged( ProcessVariableChangedEvent event ) {
        WorkflowProcessInstanceImpl instance = (WorkflowProcessInstanceImpl) event.getProcessInstance();
        processVariables = instance.getVariables();
    }

    @Override
    public void signalEvent( String type, Object event ) {
    }

    @Override
    public String[] getEventTypes() {
        return null;
    }
}