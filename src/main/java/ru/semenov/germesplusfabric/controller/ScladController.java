package ru.semenov.germesplusfabric.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.ScladFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;
import ru.semenov.germesplusfabric.service.ProductForIndividualService;
import ru.semenov.germesplusfabric.service.ScladFabricService;

import java.util.List;

@Controller
@RequestMapping("/sclad")
@AllArgsConstructor
public class ScladController {

    private final ScladFabricService scladFabricService;
    private final ProductForIndividualService productForIndividualService;

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String sclad(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<ScladFabric> sclad = scladFabricService.getByFabric(manager.getFabric());
        List<ProductForIndividual> products = productForIndividualService.getAll();

        model.addAttribute("sclad", sclad);
        model.addAttribute("products", products);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/sclad";
        return "sclad";
    }

    @PostMapping("/search")
    public String search(
            Model model,
            @AuthenticationPrincipal FabricManager manager,
            @RequestParam String query
    ) {

        List<ScladFabric> sclad = scladFabricService.getByProductName(query);
        List<ProductForIndividual> products = productForIndividualService.getAll();

        model.addAttribute("sclad", sclad);
        model.addAttribute("products", products);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/sclad";
        return "sclad";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal FabricManager manager,
            @RequestParam Long productId,
            @RequestParam Integer count
    ) {
        scladFabricService.addProduct(productId, count, manager);
        return "redirect:/sclad";
    }

    @GetMapping("/sale/{id}")
    public String sale(
            @PathVariable Long id,
            @AuthenticationPrincipal FabricManager manager,
            Model model
    ) {
        scladFabricService.sale(id, manager);
        List<ScladFabric> sclad = scladFabricService.getByFabric(manager.getFabric());
        List<ProductForIndividual> products = productForIndividualService.getAll();

        model.addAttribute("sclad", sclad);
        model.addAttribute("products", products);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/sclad";
        return "sclad";
    }
}
