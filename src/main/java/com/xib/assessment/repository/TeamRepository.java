package com.xib.assessment.repository;

import com.xib.assessment.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for Team entities.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    /**
     * Custom query to find teams without any agents and managers.
     *
     * @return a list of such teams
     */
    List<Team> findByAgentsIsEmptyAndManagersIsEmpty();
}
