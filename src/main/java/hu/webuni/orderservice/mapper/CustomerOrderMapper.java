package hu.webuni.orderservice.mapper;

import hu.webuni.orderservice.dto.CustomerOrderDTO;
import hu.webuni.orderservice.model.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {

    List<CustomerOrderDTO> toCustomerOrderDTOList(List<CustomerOrder> orders);

    List<CustomerOrder> toCustomerOrderList(List<CustomerOrderDTO> orderDTOS);

    @Mapping(source = "orderItemList", target = "orderItemDTOList")
    CustomerOrderDTO toCustomerOrderDTO(CustomerOrder customerOrder);

    @Mapping(source = "orderItemDTOList", target = "orderItemList")

    CustomerOrder toCustomerOrder(CustomerOrderDTO customerOrderDTO);





}
