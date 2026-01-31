package com.lootdeals.lootdeals.deal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DealController {

    private final DealRepository repo;

    public DealController(DealRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "q", required = false) String q, Model model) {
        if (q != null && !q.isBlank()) {
            model.addAttribute("deals", repo.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(q));
            model.addAttribute("q", q);
        } else {
            model.addAttribute("deals", repo.findTop20ByOrderByCreatedAtDesc());
        }
        return "index";
    }

    @GetMapping("/category/{category}")
    public String byCategory(@PathVariable String category, Model model) {
        model.addAttribute("category", category);
        model.addAttribute("deals", repo.findByCategoryIgnoreCaseOrderByCreatedAtDesc(category));
        return "category";
    }

    @GetMapping("/deal/{slug}")
    public String deal(@PathVariable String slug, Model model) {
        Deal d = repo.findBySlug(slug).orElseThrow();
        model.addAttribute("deal", d);
        return "deal";
    }
}

