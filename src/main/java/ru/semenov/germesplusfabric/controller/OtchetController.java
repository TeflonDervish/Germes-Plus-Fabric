package ru.semenov.germesplusfabric.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semenov.germesplusfabric.dto.OtchetDto;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.othcet.OtchetForFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.service.OtchetForFabricService;

import java.util.List;

@Controller
@RequestMapping("/otchet")
@AllArgsConstructor
public class OtchetController {

    private final OtchetForFabricService otchetForFabricService;

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String otchet(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<OtchetForFabric> otchets = otchetForFabricService.getByFabric(manager.getFabric());

        model.addAttribute("otchets", otchets);

        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/otchet";
        return "otchet";
    }

    @GetMapping("/create")
    public String createOtchet(
            @AuthenticationPrincipal FabricManager manager
    ) {
        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/create_otchet";
        return "create_otchet";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute OtchetDto otchetDto,
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        otchetForFabricService.createOtchet(manager, otchetDto);
        return "redirect:/otchet";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        List<OtchetForFabric> otchets = otchetForFabricService.getByPointOfSaleAndNameContains(manager.getFabric(), query);
        model.addAttribute("otchets", otchets);
        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/otchet";
        return "otchet";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        otchetForFabricService.deleteById(id);
        return "redirect:/otchet";
    }


    @GetMapping("/{id}")
    public String otchet(Model model,
                         @PathVariable Long id,
                         @AuthenticationPrincipal FabricManager manager
    ) {
        OtchetForFabric otchet = otchetForFabricService.getById(id);
        model.addAttribute("otchet", otchet);
        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/otchet_form";
        return "otchet_form";
    }
}
