package com.xib.assessment.repository;

import com.xib.assessment.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA Repository for Agent entities.
 */
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}
