package ru.semenov.germesplusfabric.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.feedbacks.FeedbackOnProductForIndividual;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;
import ru.semenov.germesplusfabric.service.FeedbackForIndividualService;
import ru.semenov.germesplusfabric.service.ProductForIndividualService;

import java.util.List;

@Controller
@RequestMapping("/catalog")
@AllArgsConstructor
public class CatalogController {

    private final ProductForIndividualService productForIndividualService;
    private final FeedbackForIndividualService feedbackForIndividualService;

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
    }


    @GetMapping
    public String catalog(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<ProductForIndividual> products = productForIndividualService.getAll();

        model.addAttribute("products", products);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/catalog";
        return "catalog";
    }

    @GetMapping("/create")
    public String create(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("product", new ProductForIndividual());

        return "admin/cardForProduct";
    }

    @PostMapping("/search")
    public String search(
        Model model,
        @RequestParam String query,
        @AuthenticationPrincipal FabricManager manager
    ) {
        List<ProductForIndividual> products = productForIndividualService.getByName(query);

        model.addAttribute("products", products);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/catalog";
        return "catalog";
    }

    @GetMapping("/{id}")
    public String getById(
            Model model,
            @PathVariable Long id
    ) {
        ProductForIndividual product = productForIndividualService.getById(id);
        List<FeedbackOnProductForIndividual> feedbacks = feedbackForIndividualService.getByProductForIndividual(id);

        model.addAttribute("product", product);
        model.addAttribute("feedbacks", feedbacks);

        return "admin/cardForProduct";
    }
}
