package com.assettagging.spotit.core.business.service;

import org.springframework.stereotype.Component;

import java.util.Map;

import com.assettagging.spotit.core.domain.DexDocument;
import com.assettagging.spotit.workflow.business.integration.registry.DocumentHandler;

/**
 * Created by User on 04/03/2018.
 */
@Component("dummyDocumentHandler")
public class DummyDocumentHandler implements DocumentHandler<DummyDocumentHandler.DummyDocument> {

    @Override
    public String process(DummyDocument document, Map<String, Object> variables) {
        return null;
    }


    interface DummyDocument extends DexDocument {
    }
}
