package com.project.board.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@TestComponent
public class FormDataEncoder {
    private final ObjectMapper mapper;

    public FormDataEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String encode(Object obj) {
        Map<String, String> flatMap = mapper.convertValue(obj, new TypeReference<>() {});
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.setAll(flatMap);

        return UriComponentsBuilder.newInstance()
                .queryParams(valueMap)
                .encode()
                .build()
                .getQuery();
    }
}
