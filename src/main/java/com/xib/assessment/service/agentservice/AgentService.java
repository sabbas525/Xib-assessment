package com.xib.assessment.service.agentservice;

import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.model.APIResponse;

/**
 * Service interface for Agent-related operations.
 */
public interface AgentService {
    /**
     * Retrieves all agents with pagination support.
     *
     * @param pageNumber the page number for pagination (optional)
     * @param pageSize   the size of each page (optional)
     * @return an APIResponse containing a list of agents
     */
    APIResponse findAllAgents(Integer pageNumber, Integer pageSize);

    /**
     * Retrieves a specific agent by their ID.
     *
     * @param id the ID of the agent
     * @return an APIResponse containing the agent details
     */
    APIResponse findAgentById(Long id);

    /**
     * Creates a new agent.
     *
     * @param agentDTO the data transfer object containing agent details
     * @return an APIResponse confirming the creation of the agent
     */
    APIResponse createAgent(AgentDTO agentDTO);
    /**
     * Assigns a manager to a agent.
     *
     * @param agentId the ID of the agent
     * @param managerId the ID of the manager
     * @return an APIResponse confirming the assignment
     */
    APIResponse assignManagerToAgent(Long agentId, Long managerId);
}
