package ru.semenov.germesplusfabric.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semenov.germesplusfabric.enums.OrderStatus;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.orders.OrderForLegal;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.service.OrderForFabricService;
import ru.semenov.germesplusfabric.service.OrderForLegalService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderForFabricService orderForFabricService;
    private final OrderForLegalService orderForLegalService;

    @ModelAttribute
    private void addModel(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
        model.addAttribute("status", OrderStatus.values());
    }

    @GetMapping
    public String orders(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<OrderForFabric> orderForFabrics = orderForFabricService.getByFabric(manager.getFabric());
        List<OrderForLegal> orderForLegals = orderForLegalService.getAll();

        model.addAttribute("orderForFabrics", orderForFabrics);
        model.addAttribute("orderForLegals", orderForLegals);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/orders";
        return "orders";
    }

    @PostMapping("/status")
    public String findByStatus(
            Model model,
            @RequestParam OrderStatus status,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<OrderForFabric> orderForFabrics = orderForFabricService.getByStatus(status);
        List<OrderForLegal> orderForLegals = orderForLegalService.getByStatus(status);

        model.addAttribute("orderForFabrics", orderForFabrics);
        model.addAttribute("orderForLegals", orderForLegals);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/orders";
        return "orders";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<OrderForFabric> orderForFabrics = List.of(orderForFabricService.getById(Long.parseLong(query)));
        List<OrderForLegal> orderForLegals = List.of(orderForLegalService.getById(Long.parseLong(query)));

        model.addAttribute("orderForFabrics", orderForFabrics);
        model.addAttribute("orderForLegals", orderForLegals);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/orders";
        return "orders";
    }

    @GetMapping("/fabric/{id}")
    public String fabric(
            Model model,
            @PathVariable Long id,
            @AuthenticationPrincipal FabricManager manager
    ) {
        OrderForFabric order = orderForFabricService.getById(id);

        model.addAttribute("typeOrder", "fabric");
        model.addAttribute("order", order);
        model.addAttribute("status",
                Arrays.stream(OrderStatus.values())
                        .filter(status -> status != OrderStatus.WAITING_ACCESS)
                        .toArray(OrderStatus[]::new));

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/order";
        return "order";
    }

    @GetMapping("/legal/{id}")
    public String legal(
            Model model,
            @PathVariable Long id,
            @AuthenticationPrincipal FabricManager manager
    ) {
        OrderForLegal order = orderForLegalService.getById(id);

        model.addAttribute("typeOrder", "legal");
        model.addAttribute("order", order);
        model.addAttribute("status",
                Arrays.stream(OrderStatus.values())
                        .filter(status -> status != OrderStatus.WAITING_ACCESS)
                        .toArray(OrderStatus[]::new));

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/order";
        return "order";
    }

    @PostMapping("/change-status/fabric/{id}")
    public String changeStatusFabric(
            @PathVariable Long id,
            @RequestParam String status,
            @AuthenticationPrincipal FabricManager manager
    ) {
        orderForFabricService.changeOrderStatus(id, OrderStatus.valueOf(status), manager);
        return "redirect:/order/fabric/" + id;
    }

    @PostMapping("/change-status/legal/{id}")
    public String changeStatusLegal(
            @PathVariable Long id,
            @RequestParam String status,
            @AuthenticationPrincipal FabricManager manager
    ) {
        orderForLegalService.changeOrderStatus(id, OrderStatus.valueOf(status), manager);
        return "redirect:/order/fabric/" + id;
    }


}
