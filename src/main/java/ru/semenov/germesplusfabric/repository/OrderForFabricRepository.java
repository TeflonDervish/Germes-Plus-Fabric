package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semenov.germesplusfabric.enums.OrderStatus;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.orders.OrderForLegal;

import java.time.LocalDate;
import java.util.List;

public interface OrderForFabricRepository extends JpaRepository<OrderForFabric, Long> {

    List<OrderForFabric> findByFabricId(Long id);

    List<OrderForFabric> findByStatus(OrderStatus status);

    List<OrderForFabric> findByPointOfSale_Id(Long id);

    List<OrderForFabric> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<OrderForFabric> findByOrderDateBetweenAndFabricId(LocalDate orderDate, LocalDate orderDate2, Long fabricId);
}