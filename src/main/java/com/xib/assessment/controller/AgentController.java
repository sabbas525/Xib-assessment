package com.xib.assessment.controller;


import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.service.agentservice.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests related to agents.
 * Provides API endpoints for creating, retrieving, and assigning agents.
 */
@RestController
@RequestMapping("/agent-controller")
public class AgentController {

    @Autowired
    private AgentService agentService;

    /**
     * Retrieves a list of all agents, with support for pagination.
     *
     * @param pageNumber the page number for pagination (optional)
     * @param pageSize the size of each page (optional)
     * @return a ResponseEntity containing the list of agents
     */
    @GetMapping("/agents")
    public ResponseEntity<?> getAllAgents(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return ResponseEntity.ok(agentService.findAllAgents(pageNumber, pageSize));
    }
    /**
     * Retrieves the details of a specific agent by ID.
     *
     * @param id the ID of the agent
     * @return a ResponseEntity containing the agent's details
     */
    @GetMapping("/agent/{id}")
    public ResponseEntity<?> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok(agentService.findAgentById(id));
    }
    /**
     * Creates a new agent with the given details.
     *
     * @param agentDTO the agent data transfer object containing the agent's information
     * @return a ResponseEntity indicating the result of the operation
     */
    @PostMapping("/agent")
    public ResponseEntity<?> createAgent(@RequestBody AgentDTO agentDTO) {
        return ResponseEntity.ok(agentService.createAgent(agentDTO));
    }

    /**
     * Assigns a manager to a specified agent.
     *
     * @param agentId    the ID of the agent
     * @param managerId the ID of the manager
     * @return a ResponseEntity indicating the result of the operation
     */
    @PutMapping("/assign-manager/{agentId}/manager/{managerId}")
    public ResponseEntity<?> assignManagerToAgent(@PathVariable Long agentId, @PathVariable Long managerId) {
        return ResponseEntity.ok(agentService.assignManagerToAgent(agentId, managerId));
    }
}
