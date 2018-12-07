package com.accenture.cardsproject.rest.controller;

import com.accenture.cardsproject.persistence.models.Brand;

import java.util.List;

public interface BrandController {
    void create(Brand brand);
    List<Brand> list();
    Brand getBrand(String name);
    boolean update(Brand brand);
}
