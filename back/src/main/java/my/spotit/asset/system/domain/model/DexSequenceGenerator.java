package my.spotit.asset.system.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

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
 *
 * [assetCode]
 * [locationCode]
 *
 * reference format = {#a}{#b}{#j} = prefix|year|sequence
 * <p/>
 * Example: REQ-{#b}{#e}: REQ2014000001
 *
 * @author canang technologies
 * @since 1/27/14
 */
public interface DexSequenceGenerator extends DexMetaObject {

    void setId(Long id);

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
