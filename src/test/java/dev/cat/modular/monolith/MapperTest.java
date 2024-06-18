package dev.cat.modular.monolith;

import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import dev.cat.modular.monolith.shipment.mapper.ShipmentMapper;
import dev.cat.modular.monolith.shipment.mapper.ShipmentMapperImpl;
import dev.cat.modular.monolith.shipment.mapper.StatusMapperImpl;
import dev.cat.modular.monolith.shipment.model.DeliveryStatus;
import dev.cat.modular.monolith.shipment.model.Shipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ShipmentMapperImpl.class, StatusMapperImpl.class})
public class MapperTest {

    @Autowired
    ShipmentMapper shipmentMapper;

    @Test
    void shouldMapToShipmentResponse() {

        Shipment shipment = new Shipment(
                1L, 3L, 23.5,
                "London, Rose Lane, 2, 1A, 12345",
                "London, Holland Park, 20, 17, 15577",
                DeliveryStatus.NEW, 345.0);

        ShipmentResponse response = shipmentMapper.mapToShipmentResponse(shipment);
        assertEquals(shipment.getWeight(), response.weight());

    }

}
