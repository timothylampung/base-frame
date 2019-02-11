package my.spotit.core.domain;

import java.io.Serializable;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface DexMetaObject extends Serializable {

    /**
     * entity id
     *
     * @return
     */
    Long getId();

    /**
     * metadata
     *
     * @return
     */
    DexMetadata getMetadata();

    void setMetadata(DexMetadata metadata);

    /**
     * implementing interface
     *
     * @return
     */
    Class<?> getInterfaceClass();
}

