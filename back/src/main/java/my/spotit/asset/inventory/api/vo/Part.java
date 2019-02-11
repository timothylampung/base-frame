package my.spotit.asset.inventory.api.vo;


import my.spotit.asset.core.api.MetaObject;

public class Part extends MetaObject {
    private String code;
    private String description;
    private PartCode partCode;
    //TODO Add WorkOrder
    private Long id;

    public Part() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public PartCode getPartCode() {
        return partCode;
    }

    public void setPartCode(PartCode partCode) {
        this.partCode = partCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
