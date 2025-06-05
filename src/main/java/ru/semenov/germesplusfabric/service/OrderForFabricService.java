package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.enums.OrderStatus;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.repository.OrderForFabricRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderForFabricService {

    private static final Log log = LogFactory.getLog(OrderForFabricService.class);

    private final OrderForFabricRepository orderForFabricRepository;

    private final FabricService fabricService;

//    private final KorzinaService korzinaService;
//    private final PointOfSaleService pointOfSaleService;

//    @Transactional
//    public OrderForIndividual createOrder(IndividualPerson user, OrderDto orderDto) {
//        log.info("Создание заказа для " + user.getEmail());
//
//        Korzina korzina = korzinaService.getKorzina(user);
//
//        OrderForIndividual order = new OrderForIndividual();
//        order.setIndividualPerson(user);
//        order.setProducts(new ArrayList<>(korzina.getProducts()));
//        order.setOrderDate(LocalDate.now());
//        order.setStatus(OrderStatus.WAITING_ACCESS);
//
//        Integer totalPrice = 0;
//        for (ProductForIndividual product : korzina.getProducts())
//            totalPrice += product.getPrice();
//
//        order.setTotalPrice(totalPrice);
//
//        if (orderDto.getDeliveryType().equals("delivery")) {
//            processDelivery(order, orderDto.getDeliveryDetails());
//        } else {
//            processPickup(order, orderDto.getPickupDetails());
//        }
//
//        OrderForIndividual savedOrder = orderForIndividualRepository.save(order);
//
//        korzinaService.clear(user);
//
//        return savedOrder;
//    }

//    private void processDelivery(OrderForIndividual order, DeliveryDetailsDto details) {
//        log.info("Заказ с доставкой");
//        order.setDeliveryType(DeliveryType.DELIVERY);
//        order.setDeliveryAddress(details.getAddress());
//    }

//    private void processPickup(OrderForIndividual order, PickupDetailsDto details) {
//        log.info("Самовывоз");
//        order.setDeliveryType(DeliveryType.PICKUP);
//
//        PointOfSale pointOfSale = pointOfSaleService.getById(details.getPickupPointId());
//
//        order.setPointOfSale(pointOfSale);
//        order.setOrderDate(details.getPickupDate());
//    }

    public OrderForFabric save(OrderForFabric order) {
        return orderForFabricRepository.save(order);
    }

    public List<OrderForFabric> getByFabric(Fabric fabric) {

        log.info("Получение заказов пользователя");
        return orderForFabricRepository.findByFabricId(fabric.getId());
    }

    public List<OrderForFabric> getAll() {
        log.info("Получение списка всех заказов");
        return orderForFabricRepository.findAll();
    }

    public OrderForFabric getById(Long id) {
        return orderForFabricRepository.findById(id).orElse(null);
    }

    public OrderForFabric changeOrderStatus(Long id, OrderStatus status, FabricManager manager) {
        log.info("Смена статуса заказа " + status);
        OrderForFabric order = getById(id);
        order.setStatus(status);
        return save(order);
    }

    public List<OrderForFabric> getByStatus(OrderStatus status) {
        log.info("Поиск по статусу " + status);
        return orderForFabricRepository.findByStatus(status);
    }

    public List<OrderForFabric> getByPointOfSale(Fabric fabric) {
        log.info("Выдача заказов для " + fabric.getId());
        return orderForFabricRepository.findByPointOfSale_Id(fabric.getId());
    }


    public List<OrderForFabric> getByDateBetween(LocalDate startDate, LocalDate endDate, Fabric fabric) {
        return orderForFabricRepository.findByOrderDateBetweenAndFabricId(startDate, endDate, fabric.getId());
    }
}
