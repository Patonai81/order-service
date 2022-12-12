package hu.webuni.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private OrderStatus orderStatus;

    private String userName;

    private String externalShipmentId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinTable(name = "order_to_item",
            joinColumns = { @JoinColumn(name = "fk_orderId") },
            inverseJoinColumns = { @JoinColumn(name = "fk_orderItem_Id") })
    private List<OrderItem> orderItemList = new ArrayList<>();

}
