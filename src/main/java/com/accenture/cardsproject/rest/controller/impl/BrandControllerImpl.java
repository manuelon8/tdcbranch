package com.accenture.cardsproject.rest.controller.impl;

import com.accenture.cardsproject.persistence.models.Brand;
import com.accenture.cardsproject.persistence.repository.BrandRepository;
import com.accenture.cardsproject.rest.controller.BrandController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandControllerImpl implements BrandController {
    @Autowired
    BrandRepository brandRepository;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Brand brand) {
        brandRepository.save(brand);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @Override
    public Brand getBrand(@PathVariable String name) {
        List<Brand> brandList = list();
        if(brandList != null || brandList.isEmpty()){
            for (Brand brand : brandList) {
                if(brand.getName().equals(name)){
                    return brand;
                }
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public boolean update(@RequestBody Brand brand) {
        Brand brandAux = getBrand(brand.getName());
        if(brandAux != null){
            brandAux.setName(brand.getName());
            brandAux.setServicePrice(brand.getServicePrice());
            brandRepository.save(brandAux);
            return true;
        }
        return false;
    }
}
