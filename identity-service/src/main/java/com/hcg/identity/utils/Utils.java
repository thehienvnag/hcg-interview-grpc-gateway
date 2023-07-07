package com.hcg.identity.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Utils {
    public static <T> String toJson(List<T> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }
}
