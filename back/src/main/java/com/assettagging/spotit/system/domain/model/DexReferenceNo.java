package com.assettagging.spotit.system.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 * FORMAT
 * [a] = prefix
 * [b] = long year
 * [c] = short year
 * [d] = long month
 * [e] = short month
 * [f] = long day
 * [g] = short day
 * [h] = long hour  (24)
 * [i] = short hour
 * [j] = sequence
 * reference format = {#a}{#b}{#j} = prefix|year|sequence
 * <p/>
 * Example: REQ-{#b}{#e}: REQ2014000001
 *
 * @author canang technologies
 * @since 1/27/14
 */
public interface DexReferenceNo extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    String getPrefix();

    void setPrefix(String prefix);

    String getSequenceFormat();

    void setSequenceFormat(String sequenceFormat);

    String getReferenceFormat();

    void setReferenceFormat(String referenceFormat);

    Integer getIncrementValue();

    void setIncrementValue(Integer incrementValue);

    Integer getCurrentValue();

    void setCurrentValue(Integer currentValue);
}
