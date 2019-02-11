package my.spotit.asset.system.api.vo;

import my.spotit.asset.core.api.MetaObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class SubModule extends MetaObject {
    private String code;
    private String description;

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

    @JsonCreator
    public static SubModule create(String jsonString) {
        SubModule o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, SubModule.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
