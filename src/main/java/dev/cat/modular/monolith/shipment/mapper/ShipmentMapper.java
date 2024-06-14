package dev.cat.modular.monolith.shipment.mapper;

import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import dev.cat.modular.monolith.shipment.model.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {AddressMapper.class, StatusMapper.class})
public interface ShipmentMapper {

    ShipmentMapper INSTANCE = Mappers.getMapper(ShipmentMapper.class);

    @Mappings({
            @Mapping(target = "addressTo", source = "to"),
            @Mapping(target = "addressFrom", source = "from")
    })
    Shipment mapToShipment(ShipmentRequest request);

    @Mappings({
            @Mapping(target = "to", source = "addressTo"),
            @Mapping(target = "from", source = "addressFrom")
    })
    ShipmentResponse mapToShipmentResponse(Shipment shipment);
}
