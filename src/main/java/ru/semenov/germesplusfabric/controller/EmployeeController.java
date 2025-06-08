package ru.semenov.germesplusfabric.controller;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.persons.PointManager;
import ru.semenov.germesplusfabric.service.FabricManagerService;
import ru.semenov.germesplusfabric.service.PointManagerService;
import ru.semenov.germesplusfabric.service.PointOfSaleService;

import java.util.List;

@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final FabricManagerService fabricManagerService;
    private final PointManagerService pointManagerService;
    private final PointOfSaleService pointOfSaleService;

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<PointOfSale> points = pointOfSaleService.getAll();

        model.addAttribute("manager", manager);
        model.addAttribute("points", points);
    }

    @GetMapping
    public String employee(
            Model model
    ) {
        List<FabricManager> fabricManagers = fabricManagerService.getAll();
        List<PointManager> pointManagers = pointManagerService.getAll();

        model.addAttribute("fabricManagers", fabricManagers);
        model.addAttribute("pointManagers", pointManagers);

        return "admin/employee";
    }

    @GetMapping("/fabric/{id}")
    public String pointManager(
            @PathVariable Long id,
            Model model
    ) {
        FabricManager manager = fabricManagerService.getById(id);
        model.addAttribute("manager", manager);

        return "admin/cardManFabriс";
    }

    @GetMapping("/point/{id}")
    public String fabricManager(
            @PathVariable Long id,
            Model model
    ) {
       PointManager manager = pointManagerService.getById(id);
       model.addAttribute("manager", manager);

        return "admin/cardGlavManPoint";
    }

    @GetMapping("/create/fabric")
    public String createFabric(Model model) {
        return "admin/registrationManFabric";
    }

    @PostMapping("/create/fabric")
    public String createFabric(
            @AuthenticationPrincipal FabricManager current,
            @ModelAttribute FabricManager manager,
            Model model
    ) {
        fabricManagerService.createManager(manager, current);
        return "redirect:/employee";
    }

    @GetMapping("/create/point")
    public String createPoint(Model model) {
        return "admin/registrationGlavManPoint";
    }

    @PostMapping("/create/point")
    public String createFabric(
            @RequestParam Long pointOfSaleId,
            @AuthenticationPrincipal FabricManager current,
            @ModelAttribute PointManager manager,
            Model model
    ) {
        pointManagerService.createGlavManager(manager, pointOfSaleId);
        return "redirect:/employee";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model
    ) {
        List<FabricManager> fabricManagers = fabricManagerService.getBySurname(query);
        List<PointManager> pointManagers = pointManagerService.getBySurname(query);

        model.addAttribute("fabricManagers", fabricManagers);
        model.addAttribute("pointManagers", pointManagers);

        return "admin/employee";
    }

    @PostMapping("/delete/point/{id}")
    public String deleteManPoint(
            @PathVariable Long id,
            Model model
    ) {
        pointManagerService.deleteById(id);
        return "redirect:/employee";
    }

    @PostMapping("/delete/fabric/{id}")
    public String deleteManFabric(
            @PathVariable Long id,
            Model model
    ) {
        fabricManagerService.deleteById(id);
        return "redirect:/employee";
    }

    @PostMapping("/save/fabric/{id}")
    public String saveManFabric(
            @PathVariable Long id,
            @ModelAttribute FabricManager manager,
            Model model
    ) {
        FabricManager newManager = fabricManagerService.updateManager(id, manager);
        model.addAttribute("manager", newManager);
        return "admin/cardManFabriс";
    }

    @PostMapping("/save/point/{id}")
    public String saveManPoint(
            @PathVariable Long id,
            @ModelAttribute PointManager manager,
            Model model
    ) {
        PointManager newManager = pointManagerService.update(id, manager);
        model.addAttribute("manager", newManager);
        return "admin/cardGlavManPoint";
    }

}
