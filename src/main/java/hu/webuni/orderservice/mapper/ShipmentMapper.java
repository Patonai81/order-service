package hu.webuni.orderservice.mapper;

import hu.webuni.orderservice.model.CustomerOrder;
import hu.webuni.orderservice.wsclient.ShippingOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    @Mapping(target = "deliveryAddress", source = "address")
    @Mapping(target = "pickUpAddress", ignore = true)
    @Mapping(target = "orderItemDTOList", source = "orderItemList")
    ShippingOrderDTO  toShippingOrderDTO(CustomerOrder customerOrder);
}
