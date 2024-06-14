package dev.cat.modular.monolith.shipment.mapper;

import dev.cat.modular.monolith.dto.shipment.AddressDto;
import dev.cat.modular.monolith.shipment.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto mapToAddressDto(Address address);

    Address mapToAddress(AddressDto dto);

}
