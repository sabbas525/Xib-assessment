package com.xib.assessment.repository;

import com.xib.assessment.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA Repository for Manager entities.
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
