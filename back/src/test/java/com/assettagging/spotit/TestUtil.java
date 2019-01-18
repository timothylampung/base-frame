package com.assettagging.spotit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : alif.razak@canang.com.my
 * @since : 7/5/2018 9:24 PM
 */
public class TestUtil {
    public static byte[] convertToJsonBytes(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
