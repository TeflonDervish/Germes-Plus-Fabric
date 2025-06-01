package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semenov.germesplusfabric.enums.DeliveryType;
import ru.semenov.germesplusfabric.enums.OrderStatus;
import ru.semenov.germesplusfabric.model.orders.OrderForIndividual;

import java.util.List;

@Repository
public interface OrderForIndividualRepository extends JpaRepository<OrderForIndividual, Long> {

    List<OrderForIndividual> findByIndividualPersonId(Long id);

    List<OrderForIndividual> findByStatus(OrderStatus status);

    List<OrderForIndividual> findByDeliveryType(DeliveryType deliveryType);

    List<OrderForIndividual> findByPointOfSale_Id(Long id);

}
