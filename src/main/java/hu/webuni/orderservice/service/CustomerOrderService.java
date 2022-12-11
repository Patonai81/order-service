package hu.webuni.orderservice.service;

import hu.webuni.orderservice.model.CustomerOrder;
import hu.webuni.orderservice.model.OrderStatus;
import hu.webuni.orderservice.repository.CustomerOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerOrderService {


    private final CustomerOrderRepository customerOrderRepository;
    @Transactional
    public CustomerOrder createOrder(CustomerOrder customerOrder, Authentication authentication){
        customerOrder.setUserName(((UserDetails) authentication.getPrincipal()).getUsername());
        customerOrder.setOrderStatus(OrderStatus.PENDING);
        return customerOrderRepository.save(customerOrder);
    }

}
