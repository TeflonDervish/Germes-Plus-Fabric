package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semenov.germesplusfabric.enums.OrderStatus;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.orders.OrderForIndividual;
import ru.semenov.germesplusfabric.model.orders.OrderForLegal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderForLegalRepository extends JpaRepository<OrderForLegal, Long> {

    List<OrderForLegal> findByLegalPersonId(Long id);
    List<OrderForLegal> findByOrderDateBetween(LocalDate orderDate, LocalDate orderDate2);

    List<OrderForLegal> findByStatus(OrderStatus status);
}
