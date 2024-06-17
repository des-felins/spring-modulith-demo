package dev.cat.modular.monolith.shipment.service;

import dev.cat.modular.monolith.ShipmentCreateEvent;
import dev.cat.modular.monolith.ShipmentStatusChangeEvent;
import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import dev.cat.modular.monolith.customer.globalexceptions.NotFoundException;
import dev.cat.modular.monolith.customer.globalexceptions.ValidationException;
import dev.cat.modular.monolith.shipment.ShipmentAPI;
import dev.cat.modular.monolith.shipment.mapper.ShipmentMapper;
import dev.cat.modular.monolith.shipment.model.DeliveryStatus;
import dev.cat.modular.monolith.shipment.model.Shipment;
import dev.cat.modular.monolith.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentService implements ShipmentAPI {

    private final ShipmentRepository shipmentRepository;
    private final ApplicationEventPublisher events;

    @Override
    @Transactional
    public ShipmentResponse createOrder(ShipmentRequest request) {

        Shipment shipment = ShipmentMapper.INSTANCE.mapToShipment(request);

        if (shipment.getAddressTo().equals(shipment.getAddressFrom())) {
            throw new ValidationException("Shipment must be delivered to a different address.");
        }

        shipment.setDeliveryStatus(DeliveryStatus.NEW);

        Shipment newShipment = shipmentRepository.save(shipment);
        events.publishEvent(new ShipmentCreateEvent(newShipment.getId()));
        return ShipmentMapper.INSTANCE.mapToShipmentResponse(newShipment);

    }

    @Override
    public List<ShipmentResponse> findOrdersByCustomerId(Long id) {
        List<Shipment> shipments = shipmentRepository.findByCustomerId(id);
        return shipments.stream().map(ShipmentMapper.INSTANCE::mapToShipmentResponse).toList();
    }

    @Override
    @Transactional
    public void updateShipmentStatus(Long id, String status) {
        Optional<Shipment> shipmentOpt = shipmentRepository.findById(id);
        if (shipmentOpt.isEmpty()) {
            throw new NotFoundException("Couldn't find shipment with id " + id);
        }
        Shipment shipment = shipmentOpt.get();
        shipment.setDeliveryStatus(DeliveryStatus.valueOf(status));
        shipmentRepository.save(shipment);
        events.publishEvent(new ShipmentStatusChangeEvent(id));

    }

}
