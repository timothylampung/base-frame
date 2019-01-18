package com.assettagging.spotit.common.api.vo;

import com.assettagging.spotit.core.api.MetaObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author canang technologies
 */
public class PositionCode extends MetaObject {
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
    public static PositionCode create(String jsonString) {
    PositionCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, PositionCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
