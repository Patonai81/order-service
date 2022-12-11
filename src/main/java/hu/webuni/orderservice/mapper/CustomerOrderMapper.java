package hu.webuni.orderservice.mapper;

import hu.webuni.orderservice.dto.AddressDTO;
import hu.webuni.orderservice.dto.CustomerOrderDTO;
import hu.webuni.orderservice.dto.OrderItemDTO;
import hu.webuni.orderservice.model.Address;
import hu.webuni.orderservice.model.CustomerOrder;
import hu.webuni.orderservice.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {

    @Mapping(source = "orderItemList", target = "orderItemDTOList")
    CustomerOrderDTO toCustomerOrderDTO(CustomerOrder customerOrder);

    @Mapping(source = "orderItemDTOList", target = "orderItemList")

    CustomerOrder toCustomerOrder(CustomerOrderDTO customerOrderDTO);





}
