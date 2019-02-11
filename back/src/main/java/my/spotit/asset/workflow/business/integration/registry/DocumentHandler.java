package my.spotit.asset.workflow.business.integration.registry;


import java.util.Map;

import my.spotit.asset.core.domain.DexDocument;

/**
 * @author canang technologies
 */
public interface DocumentHandler<T extends DexDocument> {

    /**
     * handle document processing according to its type
     *
     * @param document
     * @param variables
     * @return
     */
    String process(T document, Map<String, Object> variables);
}
