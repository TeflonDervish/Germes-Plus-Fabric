package ru.semenov.germesplusfabric.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;

@Controller
@RequestMapping("/account")
public class AccountController {

    @ModelAttribute
    public void modelAttributes(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String account(
            Model model,
            @AuthenticationPrincipal FabricManager manager
    ) {


        if (manager.getRole().equals(RoleFabric.ADMIN)) return "admin/account";
        return "account";
    }
}
