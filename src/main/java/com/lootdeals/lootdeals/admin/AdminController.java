package com.lootdeals.lootdeals.admin;

import com.lootdeals.lootdeals.deal.Deal;
import com.lootdeals.lootdeals.deal.DealRepository;
import com.lootdeals.lootdeals.deal.DealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DealRepository repo;
    private final DealService service;

    public AdminController(DealRepository repo, DealService service) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping("/login")
    public String login() {
        return "admin-login";
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("deals", repo.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/new")
    public String newDeal(Model model) {
        model.addAttribute("deal", new Deal());
        return "admin-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Deal deal) {
        service.save(deal);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("deal", repo.findById(id).orElseThrow());
        return "admin-form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin";
    }
}
