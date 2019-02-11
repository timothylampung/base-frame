package my.spotit.asset.workflow.business.integration.registry;

import my.spotit.asset.core.domain.DexDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * @author canang technologies
 */
@Component("documentHandlerRegistry")
public class DocumentHandlerRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentHandlerRegistry.class);

    public Map<Class<?>, DocumentHandler<? extends DexDocument>> handlers =
            new HashMap<Class<?>, DocumentHandler<? extends DexDocument>>();

    @Autowired
    private List<DocumentHandler> myHandlers;

    @PostConstruct
    public void initHandlers() {
        Collections.sort(myHandlers, AnnotationAwareOrderComparator.INSTANCE);
        for (DocumentHandler myHandler : myHandlers) {
            LOG.info("Initing handler {}", myHandler.getClass().getName());
            installHandler(myHandler);
        }
    }

    public <T> void installHandler(DocumentHandler<?> documentHandler) {
        handlers.put(extractGenericType(documentHandler), documentHandler);
    }

    public <T> String process(DexDocument obj, Map variables) {
        DocumentHandler<DexDocument> documentHandler = getHandler(obj);
        if (documentHandler != null)
            return documentHandler.process(obj, variables);
        else
            return obj.toString();
    }

    @SuppressWarnings("unchecked")
    private <T> DocumentHandler<DexDocument> getHandler(DexDocument obj) {
        DocumentHandler<? extends DexDocument> handler = handlers.get(obj.getInterfaceClass());
        if (null == handler) handler = handlers.get(obj.getClass().getGenericInterfaces()[0]);
        return (DocumentHandler<DexDocument>) handler;
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> extractGenericType(DocumentHandler<?> documentHandler) {
        Type[] interfaces = documentHandler.getClass().getGenericInterfaces();
        for (Type type : interfaces)
            if (type instanceof ParameterizedType)
                return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        throw new IllegalArgumentException("You must supply a generified DocumentHandler with a single parameterized type");
    }

}
