package com.accenture.cardsproject.persistence.repository;

import com.accenture.cardsproject.persistence.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
