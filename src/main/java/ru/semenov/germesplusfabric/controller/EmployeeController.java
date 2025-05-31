package ru.semenov.germesplusfabric.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.persons.PointManager;
import ru.semenov.germesplusfabric.service.FabricManagerService;
import ru.semenov.germesplusfabric.service.PointManagerService;

import java.util.List;

@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final FabricManagerService fabricManagerService;
    private final PointManagerService pointManagerService;

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
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
        return "admin/cardManFabriс";
    }

    @GetMapping("/point/{id}")
    public String fabricManager(
            @PathVariable Long id,
            Model model
    ) {
        return "admin/cardGlavManPoint";
    }

    @GetMapping("/create/fabric")
    public String createFabric(Model model) {
        return "admin/registrationGlavManPoint";
    }

    @GetMapping("/create/point")
    public String createPoint(Model model) {
        return "admin/registrationManFabric";
    }
}
