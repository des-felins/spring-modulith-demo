package dev.cat.modular.monolith.shipment.util;

import dev.cat.modular.monolith.shipment.model.DeliveryStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<DeliveryStatus, String> {
    @Override
    public String convertToDatabaseColumn(DeliveryStatus status) {
        if (status == null) {
            return null;
        }
        return status.getName();
    }

    @Override
    public DeliveryStatus convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }

        return Stream.of(DeliveryStatus.values())
                .filter(n -> n.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
