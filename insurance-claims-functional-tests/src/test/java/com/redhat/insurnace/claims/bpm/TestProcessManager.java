package com.redhat.insurnace.claims.bpm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.process.instance.event.DefaultSignalManagerFactory;
import org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory;
import org.jbpm.runtime.manager.impl.DefaultRegisterableItemsFactory;
import org.jbpm.runtime.manager.impl.SimpleRegisterableItemsFactory;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.kie.api.KieServices;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.io.ResourceType;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.task.TaskLifeCycleEventListener;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

/**
 * 
 * 
 * 
 *
 */
public class TestProcessManager extends JbpmJUnitBaseTestCase {

    private KieSession ksession;
    private long processInstanceId;
    private CucumberProcessEventListener event;
    private KieRuntimeLogger audit = null;
    private boolean auditLogEnabled;
    private String auditLogName;

    public TestProcessManager() {
        super();
        sessionPersistence = true;
        setupDataSource = true;
        userGroupCallback = new JBossUserGroupCallbackImpl( "config/test.users.properties" );
        setupPoolingDataSource();
        event = new CucumberProcessEventListener();
        createClasspathRuntimeManager();
    }

    public void startProcess( String process, Map<String, Object> processVariables, Map<String, WorkItemHandler> handlers ) {
        ksession = getRuntimeEngine().getKieSession();

        if ( auditLogEnabled ) {
            audit = KieServices.Factory.get().getLoggers().newFileLogger( ksession, "target/" + auditLogName );
        }

        event = new CucumberProcessEventListener();
        ksession.addEventListener( event );

        // register the test/mocked implementations of WIHs
        for ( String name : handlers.keySet() ) {
            ksession.getWorkItemManager().registerWorkItemHandler( name, handlers.get( name ) );
        }

        WorkflowProcessInstance instance = (WorkflowProcessInstance) ksession.startProcess( process, processVariables );
        processInstanceId = instance.getId();
    }

    public void completeTask( String taskName, Map<String, Object> data ) {
        TaskService taskService = getRuntimeEngine().getTaskService();
        TaskSummary currTask = null;
        for ( TaskSummary task : taskService.getTasksByGroup( Arrays.asList( "admin" ) ) ) {
            // in the case that multiple scenarios run process instances then we need to verify that we are claiming the right task
            // TODO - is there a better way to do this?
            if ( task.getName().equals( taskName ) && task.getStatus().equals( Status.Ready ) && processInstanceId == task.getProcessInstanceId() ) {
                currTask = task;
            }
        }
        if ( !currTask.equals( null ) ) {
            assertTrue( currTask.getName().equals( taskName ) );
            taskService.claim( currTask.getId(), "michael" );
            taskService.start( currTask.getId(), "michael" );
            taskService.complete( currTask.getId(), "michael", data );
            assertEquals( currTask.getStatus(), Status.Completed );
        }
    }

    public void enableAuditLog( String auditLogName ) {
        this.auditLogEnabled = true;
        this.auditLogName = auditLogName;
    }

    public Object getProcessVariable( String name ) {
        return event.getProcessVariables().get( name );
    }

    protected RuntimeManager createClasspathRuntimeManager() {
        return createClasspathRuntimeManager( Strategy.SINGLETON, null, null );
    }

    public void reset() {
        auditLogEnabled = false;
        if ( audit != null ) {
            audit.close();
        }
        auditLogName = null;
        this.processInstanceId = Integer.MIN_VALUE;
        event = null;
    }

    /**
     * 
     * This is adapted from the JBPMJUnitTestCase code, we need a way to build
     * from a KieBase rather than raw bpmn2 files
     * 
     * @param strategy
     * @param resources
     * @param identifier
     */
    protected RuntimeManager createClasspathRuntimeManager( Strategy strategy, Map<String, ResourceType> resources, String identifier ) {
        if ( manager != null ) {
            throw new IllegalStateException( "There is already one RuntimeManager active" );
        }

        RuntimeEnvironmentBuilder builder = null;
        if ( !setupDataSource ) {
            //@formatter:off
            builder = RuntimeEnvironmentBuilder.Factory.get()
                    .newClasspathKmoduleDefaultBuilder() // this is the only real difference from the rest of the JBPMJUnitTestCase code
                    .addConfiguration( "drools.processSignalManagerFactory", DefaultSignalManagerFactory.class.getName() )
                    .addConfiguration( "drools.processInstanceManagerFactory", DefaultProcessInstanceManagerFactory.class.getName() )
                    .registerableItemsFactory( new SimpleRegisterableItemsFactory() {

                        @Override
                        public Map<String, WorkItemHandler> getWorkItemHandlers( RuntimeEngine runtime ) {
                            Map<String, WorkItemHandler> handlers = new HashMap<String, WorkItemHandler>();
                            handlers.putAll( super.getWorkItemHandlers( runtime ) );
                            handlers.putAll( customHandlers );
                            return handlers;
                        }

                        @Override
                        public List<ProcessEventListener> getProcessEventListeners( RuntimeEngine runtime ) {
                            List<ProcessEventListener> listeners = super.getProcessEventListeners( runtime );
                            listeners.addAll( customProcessListeners );
                            return listeners;
                        }

                        @Override
                        public List<AgendaEventListener> getAgendaEventListeners( RuntimeEngine runtime ) {
                            List<AgendaEventListener> listeners = super.getAgendaEventListeners( runtime );
                            listeners.addAll( customAgendaListeners );
                            return listeners;
                        }

                        @Override
                        public List<TaskLifeCycleEventListener> getTaskListeners() {
                            List<TaskLifeCycleEventListener> listeners = super.getTaskListeners();
                            listeners.addAll( customTaskListeners );
                            return listeners;
                        }

                    } );
            //@formatter:on

        }
        else if ( sessionPersistence ) {
            //@formatter:off
            builder = RuntimeEnvironmentBuilder.Factory.get()
                    .newClasspathKmoduleDefaultBuilder()
                    .entityManagerFactory( this.getEmf() )
                    .registerableItemsFactory( new DefaultRegisterableItemsFactory() {

                @Override
                public Map<String, WorkItemHandler> getWorkItemHandlers( RuntimeEngine runtime ) {
                    Map<String, WorkItemHandler> handlers = new HashMap<String, WorkItemHandler>();
                    handlers.putAll( super.getWorkItemHandlers( runtime ) );
                    handlers.putAll( customHandlers );
                    return handlers;
                }

                @Override
                public List<ProcessEventListener> getProcessEventListeners( RuntimeEngine runtime ) {
                    List<ProcessEventListener> listeners = super.getProcessEventListeners( runtime );
                    listeners.addAll( customProcessListeners );
                    return listeners;
                }

                @Override
                public List<AgendaEventListener> getAgendaEventListeners( RuntimeEngine runtime ) {
                    List<AgendaEventListener> listeners = super.getAgendaEventListeners( runtime );
                    listeners.addAll( customAgendaListeners );
                    return listeners;
                }

                @Override
                public List<TaskLifeCycleEventListener> getTaskListeners() {
                    List<TaskLifeCycleEventListener> listeners = super.getTaskListeners();
                    listeners.addAll( customTaskListeners );
                    return listeners;
                }

            } );
            //formatter:on
        }
        else {
            //@formatter:off
            builder = RuntimeEnvironmentBuilder.Factory.get()
                    .newClasspathKmoduleDefaultBuilder()
                    .entityManagerFactory( this.getEmf() )
                    .registerableItemsFactory( new DefaultRegisterableItemsFactory() {

                @Override
                public Map<String, WorkItemHandler> getWorkItemHandlers( RuntimeEngine runtime ) {
                    Map<String, WorkItemHandler> handlers = new HashMap<String, WorkItemHandler>();
                    handlers.putAll( super.getWorkItemHandlers( runtime ) );
                    handlers.putAll( customHandlers );
                    return handlers;
                }

                @Override
                public List<ProcessEventListener> getProcessEventListeners( RuntimeEngine runtime ) {
                    List<ProcessEventListener> listeners = super.getProcessEventListeners( runtime );
                    listeners.addAll( customProcessListeners );
                    return listeners;
                }

                @Override
                public List<AgendaEventListener> getAgendaEventListeners( RuntimeEngine runtime ) {
                    List<AgendaEventListener> listeners = super.getAgendaEventListeners( runtime );
                    listeners.addAll( customAgendaListeners );
                    return listeners;
                }

                @Override
                public List<TaskLifeCycleEventListener> getTaskListeners() {
                    List<TaskLifeCycleEventListener> listeners = super.getTaskListeners();
                    listeners.addAll( customTaskListeners );
                    return listeners;
                }

            } );
            //@formatter:on
        }

        builder.userGroupCallback( userGroupCallback );
        return createRuntimeManager( strategy, resources, builder.get(), identifier );
    }
}