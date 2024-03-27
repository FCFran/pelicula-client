package com.dusseldorf.enums.converters;

import com.dusseldorf.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
     return   status == null ? null : status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        return value == null ? null :
                Stream.of(Status.values()).filter(status -> status.getValue().equals(value))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);
    }
}
