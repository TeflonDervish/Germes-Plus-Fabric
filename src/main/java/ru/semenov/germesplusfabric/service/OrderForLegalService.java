package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.enums.OrderStatus;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.orders.OrderForLegal;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.persons.LegalPerson;
import ru.semenov.germesplusfabric.repository.OrderForLegalRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderForLegalService {

    private static final Log log = LogFactory.getLog(OrderForLegalService.class);

    private final OrderForLegalRepository orderForLegalRepository;
    private final FabricService fabricService;


    public OrderForLegal save(OrderForLegal order) {
        return orderForLegalRepository.save(order);
    }

    public List<OrderForLegal> getOrderForLegal(LegalPerson user) {
        log.info("Получение заказов пользователя");
        return orderForLegalRepository.findByLegalPersonId(user.getId());
    }

    public List<OrderForLegal> getAll() {
        log.info("Получение списка всех заказов");
        return orderForLegalRepository.findAll();
    }

    public List<OrderForLegal> getByStatus(OrderStatus status) {
        log.info("Поиск по статусу " + status);
        return orderForLegalRepository.findByStatus(status);
    }

    public OrderForLegal getById(Long id) {
        log.info("Получение по id " + id);
        return orderForLegalRepository.findById(id).orElse(null);
    }

    public List<OrderForLegal> getByDateBetween(LocalDate startDate, LocalDate endDate) {
        return orderForLegalRepository.findByOrderDateBetween(startDate, endDate);
    }

    public OrderForLegal changeOrderStatus(Long id, OrderStatus status, FabricManager manager) {
        log.info("Смена статуса заказа " + status);
        OrderForLegal order = getById(id);
        order.setStatus(status);
        return save(order);
    }
}
