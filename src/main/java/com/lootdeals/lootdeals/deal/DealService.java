package com.lootdeals.lootdeals.deal;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;

@Service
public class DealService {
    private final DealRepository repo;

    public DealService(DealRepository repo) {
        this.repo = repo;
    }

    public Deal save(Deal d) {
        if (d.getSlug() == null || d.getSlug().isBlank()) {
            d.setSlug(slugify(d.getTitle()));
        }

        String base = d.getSlug();
        int i = 2;

        while (repo.findBySlug(d.getSlug()).isPresent()) {
            Deal existing = repo.findBySlug(d.getSlug()).get();
            if (d.getId() != null && existing.getId().equals(d.getId())) break;
            d.setSlug(base + "-" + i++);
        }

        return repo.save(d);
    }

    public static String slugify(String input) {
        String s = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        s = s.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "-");
        s = s.replaceAll("(^-|-$)", "");
        return s.isBlank() ? "deal" : s;
    }
}
