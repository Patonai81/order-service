package hu.webuni.orderservice.repository;

import hu.webuni.orderservice.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

    @Query("SELECT DISTINCT c FROM CustomerOrder c INNER JOIN FETCH c.orderItemList l where c.userName= :userName")
    List<CustomerOrder> findByUserName(String userName);

    @Query("SELECT DISTINCT c from CustomerOrder  c where c.externalShipmentId= :externalId")
    Optional<CustomerOrder> findByExternalId(String externalId);

}
