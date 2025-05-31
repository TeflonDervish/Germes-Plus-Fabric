package ru.semenov.germesplusfabric.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.persons.PointManager;
import ru.semenov.germesplusfabric.service.PointOfSaleService;

import java.util.List;

@Controller
@RequestMapping("/point")
@AllArgsConstructor
public class PointController {

    private final PointOfSaleService pointOfSaleService;

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String point(
            Model model
    ) {
        List<PointOfSale> points = pointOfSaleService.getAll();

        model.addAttribute("points", points);

        return "admin/points";
    }

    @GetMapping("/{id}")
    public String getById(
            @PathVariable Long id,
            Model model
    ) {
        PointOfSale point = pointOfSaleService.getById(id);

        model.addAttribute("point", point);

        return "admin/point";
    }

    @GetMapping("/create")
    public String create(
            Model model
    ) {
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setPointManager(new PointManager());

        model.addAttribute("point", pointOfSale);

        return "admin/point";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model
    ) {
        List<PointOfSale> points = pointOfSaleService.getByName(query);

        model.addAttribute("points", points);

        return "admin/points";
    }


}
