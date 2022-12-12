package hu.webuni.orderservice.service;

import hu.webuni.orderservice.mapper.ShipmentMapper;
import hu.webuni.orderservice.model.CustomerOrder;
import hu.webuni.orderservice.model.OrderStatus;
import hu.webuni.orderservice.repository.CustomerOrderRepository;
import hu.webuni.orderservice.wsclient.CustomerShipment;
import hu.webuni.orderservice.wsclient.CustomerShipmentImplService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerOrderService {


    public static final String ADMIN = "admin";
    private final CustomerOrderRepository customerOrderRepository;
    private final ShipmentMapper shipmentMapper;

    @Transactional
    public CustomerOrder createOrder(CustomerOrder customerOrder, Authentication authentication) {
        if (null == customerOrder.getUserName()) {
            customerOrder.setUserName(((UserDetails) authentication.getPrincipal()).getUsername());
        }
        customerOrder.setOrderStatus(OrderStatus.PENDING);
        return customerOrderRepository.save(customerOrder);
    }

    //ezeket nem lehet normálisan összekombinálni
    // @PreAuthorize("hasAuthority('admin') or #username == authentication.principal.username")
    public List<CustomerOrder> findOrders(String userName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!((UserDetails) authentication.getPrincipal()).getAuthorities().contains(new SimpleGrantedAuthority(ADMIN))) {
            userName = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        log.info("User name: " + userName);
        return customerOrderRepository.findByUserName(userName);
    }

    @Transactional
    public Optional<CustomerOrder> changeOrderStatus(Long orderId, String status) throws IllegalArgumentException {
        Optional<CustomerOrder> result = Optional.ofNullable(null);
        OrderStatus orderStatus = OrderStatus.valueOf(status);

        if (orderStatus.equals(OrderStatus.CONFIRMED) || orderStatus.equals(OrderStatus.DECLINED)) {
            result = customerOrderRepository.findById(orderId);
            if (result.isPresent()) {
                CustomerOrder customerOrder = result.get();
                customerOrder.setOrderStatus(orderStatus);
                if (orderStatus.equals(OrderStatus.CONFIRMED)){
                    customerOrder.setExternalShipmentId(sendOrder(customerOrder));
                }

            }

        } else {
            log.error("This orderstatus cannot be set manually:  " + status);
        }
        return result;
    }

    @Transactional
    public String sendOrder(CustomerOrder customerOrder){
        CustomerShipmentImplService shipmentImplService = new CustomerShipmentImplService();
        CustomerShipment customerShipment = shipmentImplService.getCustomerShipmentImplPort();
        String generatedId=  customerShipment.shipOrder(shipmentMapper.toShippingOrderDTO(customerOrder));
        log.info("Generated id has arrived: "+generatedId);
        return generatedId;
    }

}
