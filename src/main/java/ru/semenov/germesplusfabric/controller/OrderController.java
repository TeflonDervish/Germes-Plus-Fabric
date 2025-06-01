package ru.semenov.germesplusfabric.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public String orders(
            Model model,
            @AuthenticationPrincipal FabricManager manager
            ) {


        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/orders";
        return "orders";
    }
}
