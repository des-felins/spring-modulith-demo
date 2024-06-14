package dev.cat.modular.monolith.shipment.service;

import dev.cat.modular.monolith.ShipmentCreateEvent;
import dev.cat.modular.monolith.ShipmentStatusChangeEvent;
import dev.cat.modular.monolith.calculator.CalculatorAPI;
import dev.cat.modular.monolith.dto.calculator.CalculatorRequest;
import dev.cat.modular.monolith.globalexceptions.NotFoundException;
import dev.cat.modular.monolith.globalexceptions.ValidationException;
import dev.cat.modular.monolith.shipment.ShipmentAPI;
import dev.cat.modular.monolith.dto.shipment.AddressDto;
import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import dev.cat.modular.monolith.shipment.mapper.AddressMapper;
import dev.cat.modular.monolith.shipment.mapper.ShipmentMapper;
import dev.cat.modular.monolith.shipment.model.Address;
import dev.cat.modular.monolith.shipment.model.DeliveryStatus;
import dev.cat.modular.monolith.shipment.model.Shipment;
import dev.cat.modular.monolith.shipment.repository.AddressRepository;
import dev.cat.modular.monolith.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentService implements ShipmentAPI {

    private final ShipmentRepository shipmentRepository;
    private final AddressRepository addressRepository;
    private final CalculatorAPI calculatorAPI;
    private final ApplicationEventPublisher events;

    @Override
    @Transactional
    public ShipmentResponse createOrder(ShipmentRequest request) {
        Address addressTo;
        Address addressFrom;

        double verifiedPrice = calculatorAPI.calculatePrice(new CalculatorRequest(request.weight(), request.from(), request.to()));

        if (verifiedPrice != request.price()) {
            throw new ValidationException("Shipment price doesn't match the requested price.");
        }
        Shipment shipment = ShipmentMapper.INSTANCE.mapToShipment(request);

        if (shipment.getAddressTo().getId() == null) {
            addressTo = addressRepository.save(shipment.getAddressTo());
            shipment.setAddressTo(addressTo);
        }

        if (shipment.getAddressFrom().getId() == null) {
            addressFrom = addressRepository.save(shipment.getAddressFrom());
            shipment.setAddressFrom(addressFrom);
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
    public AddressDto saveAddress(AddressDto dto) {
        if (dto.id() != null) {
            throw new ValidationException("Address with id " + dto.id() + " already exists.");
        }
        Address address = AddressMapper.INSTANCE.mapToAddress(dto);
        return AddressMapper.INSTANCE.mapToAddressDto(addressRepository.save(address));
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
