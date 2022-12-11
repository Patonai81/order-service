package hu.webuni.orderservice.service;

import hu.webuni.orderservice.model.CustomerOrder;
import hu.webuni.orderservice.model.OrderStatus;
import hu.webuni.orderservice.repository.CustomerOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerOrderService {


    private final CustomerOrderRepository customerOrderRepository;
    @Transactional
    public CustomerOrder createOrder(CustomerOrder customerOrder, Authentication authentication){
        if (null == customerOrder.getUserName()){
            customerOrder.setUserName(((UserDetails) authentication.getPrincipal()).getUsername());
        }
        customerOrder.setOrderStatus(OrderStatus.PENDING);
        return customerOrderRepository.save(customerOrder);
    }


  // @PreAuthorize("hasAuthority('admin') or (#username == authentication.principal.username)")
    public List<CustomerOrder> findOrders(String userName){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if (!((UserDetails)authentication.getPrincipal()).getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            userName= ((UserDetails)authentication.getPrincipal()).getUsername();
       }
        log.info("User name: "+userName);
       return customerOrderRepository.findByUserName(userName);
    }

}
