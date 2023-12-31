package com.kafka_sample_project.emailservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka_sample_project.emailservice.dto.TestDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class TestDtoDeserializer implements Deserializer<TestDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional config needed
    }

    @Override
    public TestDto deserialize(String topic, byte[] data) {
        if (data == null)
            return null;

        try {
            return objectMapper.readValue(data, TestDto.class);
        } catch (IOException e) {
            throw new SerializationException("Error deserializing TestDto", e);
        }
    }

    @Override
    public void close() {
        // No resource to close
    }
}
