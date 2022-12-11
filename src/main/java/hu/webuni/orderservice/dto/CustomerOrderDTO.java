package hu.webuni.orderservice.dto;

import hu.webuni.orderservice.model.Address;
import hu.webuni.orderservice.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerOrderDTO {

    private Long id;
    private OrderStatus orderStatus;
    private String userName;
    private AddressDTO address;
    private List<OrderItemDTO> orderItemDTOList;

}
