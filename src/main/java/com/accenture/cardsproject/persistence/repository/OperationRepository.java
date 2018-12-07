package com.accenture.cardsproject.persistence.repository;

import com.accenture.cardsproject.persistence.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
