package my.spotit.asset.core.business.service;

import org.springframework.stereotype.Component;

import java.util.Map;

import my.spotit.asset.core.domain.DexDocument;
import my.spotit.asset.workflow.business.integration.registry.DocumentHandler;

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
