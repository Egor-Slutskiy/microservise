package com.example.consumer.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Payment.class, name = "payment")
})
public interface Payload {
}
