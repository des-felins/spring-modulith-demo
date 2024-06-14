package dev.cat.modular.monolith;

import dev.cat.modular.monolith.dto.shipment.AddressDto;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import dev.cat.modular.monolith.shipment.mapper.*;
import dev.cat.modular.monolith.shipment.model.Address;
import dev.cat.modular.monolith.shipment.model.DeliveryStatus;
import dev.cat.modular.monolith.shipment.model.Shipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {AddressMapperImpl.class, ShipmentMapperImpl.class, StatusMapperImpl.class})
public class MapperTest {

    @Autowired
    ShipmentMapper shipmentMapper;

    @Test
    void shouldMapToShipmentDTO() {

        Set<Shipment> shipmentTo = new HashSet<>();
        Set<Shipment> shipmentFrom = new HashSet<>();
        Address addressFrom = new Address(
                1L,
                "London", "Rose Lane", "2", "1A", "12345",
                shipmentTo, shipmentFrom);
        Set<Shipment> shipmentTo2 = new HashSet<>();
        Set<Shipment> shipmentFrom2 = new HashSet<>();

        Address addressTo = new Address(
                2L,
                "London", "Holland Park", "20", "17", "15577",
                shipmentTo2, shipmentFrom2);

        Shipment shipment = new Shipment(1L, 3L, 23.5, addressFrom, addressTo, DeliveryStatus.NEW, 345.0);

        ShipmentResponse response = shipmentMapper.mapToShipmentResponse(shipment);
        assertEquals(shipment.getWeight(), response.weight());

    }

    @Test
    void shouldMapToAddressDTO(){
        Set<Shipment> shipmentTo = new HashSet<>();
        Set<Shipment> shipmentFrom = new HashSet<>();
        Address addressFrom = new Address(
                1L,
                "London", "Rose Lane", "2", "1A", "12345",
                shipmentTo, shipmentFrom);

        AddressDto dto = AddressMapper.INSTANCE.mapToAddressDto(addressFrom);
        assertEquals(addressFrom.getStreet(), dto.street());

    }

}
