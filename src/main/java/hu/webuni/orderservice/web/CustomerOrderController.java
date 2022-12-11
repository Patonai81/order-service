package hu.webuni.orderservice.web;

import hu.webuni.orderservice.dto.CustomerOrderDTO;
import hu.webuni.orderservice.mapper.CustomerOrderMapper;
import hu.webuni.orderservice.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {

    private final CustomerOrderMapper customerOrderMapper;
    private final CustomerOrderService customerOrderService;

    @PostMapping("/create")
    public CustomerOrderDTO createOrder(@RequestBody CustomerOrderDTO customerOrderDTO,@CurrentSecurityContext(expression = "authentication")
    Authentication authentication){
        return customerOrderMapper.toCustomerOrderDTO(customerOrderService.createOrder(customerOrderMapper.toCustomerOrder(customerOrderDTO),authentication));
    }

    @GetMapping("/find")
    public List<CustomerOrderDTO> findOrder(String userName){
           return customerOrderMapper.toCustomerOrderDTOList(customerOrderService.findOrders(userName));
    }

}
