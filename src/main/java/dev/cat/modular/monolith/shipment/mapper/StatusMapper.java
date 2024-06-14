package dev.cat.modular.monolith.shipment.mapper;

import dev.cat.modular.monolith.shipment.model.DeliveryStatus;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface StatusMapper {
    String mapToStatusName(DeliveryStatus status);
    DeliveryStatus mapToStatus(String status);
}
