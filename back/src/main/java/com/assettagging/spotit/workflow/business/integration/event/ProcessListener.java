package com.assettagging.spotit.workflow.business.integration.event;

import com.assettagging.spotit.core.domain.DexDocument;
import com.assettagging.spotit.workflow.business.integration.registry.DocumentHandlerRegistry;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author canang technologies
 */
@Component("processListener")
public class ProcessListener implements ApplicationListener<ProcessEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessListener.class);

    @Autowired
    protected ProcessEngine processEngine;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired(required = false)
    private DocumentHandlerRegistry registry;

    @Override
    public void onApplicationEvent(ProcessEvent event) {
        LOG.info("receiving process event");
        DexDocument document = event.getDocument();
        Map variables = event.getVariables();
        registry.process(document, variables);
    }
}
