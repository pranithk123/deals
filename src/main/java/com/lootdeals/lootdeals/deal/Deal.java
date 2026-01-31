package com.lootdeals.lootdeals.deal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(unique = true)
    private String slug;

    @NotBlank
    private String category; // Laptops, Mobiles, etc.

    private String store;    // Amazon, Flipkart...

    private double price;
    private double oldPrice;

    @Column(length = 2000)
    private String description;

    @NotBlank
    @Column(length = 2000)
    private String affiliateUrl;

    private Instant createdAt = Instant.now();

    private Instant expiresAt; // null = never expires

    @Transient
    public boolean getExpired() {
        return expiresAt != null && Instant.now().isAfter(expiresAt);
    }

    @Transient
    public int getDiscountPercent() {
        if (oldPrice <= 0 || price <= 0 || oldPrice <= price) return 0;
        return (int) Math.round(((oldPrice - price) / oldPrice) * 100.0);
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getOldPrice() { return oldPrice; }
    public void setOldPrice(double oldPrice) { this.oldPrice = oldPrice; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAffiliateUrl() { return affiliateUrl; }
    public void setAffiliateUrl(String affiliateUrl) { this.affiliateUrl = affiliateUrl; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
}
