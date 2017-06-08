package com.redhat.insurance.claims.test.wih;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import com.redhat.insurance.claims.model.Policy;

/**
 * Probaby should look into using mockito or something
 *
 */
public class TestPolicyLookupWorkItemHandler implements WorkItemHandler {

    private Policy policy;

    public TestPolicyLookupWorkItemHandler( Policy policy ) {
        this.policy = policy;
    }

    @Override
    public void executeWorkItem( WorkItem workItem, WorkItemManager manager ) {
        Map<String, Object> results = new HashMap<String, Object>();
        results.put( "policy", policy );
        manager.completeWorkItem( workItem.getId(), results );
    }

    @Override
    public void abortWorkItem( WorkItem workItem, WorkItemManager manager ) {
        manager.abortWorkItem( workItem.getId() );
    }

}
