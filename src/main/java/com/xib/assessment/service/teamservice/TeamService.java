package com.xib.assessment.service.teamservice;


import com.xib.assessment.mapper.teammapper.TeamDTO;
import com.xib.assessment.model.APIResponse;

/**
 * Service interface for Team-related operations.
 */
public interface TeamService {
    /**
     * Retrieves all teams.
     *
     * @return an APIResponse containing a list of teams
     */

    APIResponse findAllTeams();
    /**
     * Retrieves a team by its ID.
     *
     * @param id the ID of the team
     * @return an APIResponse containing the team details
     */
    APIResponse findTeamById(Long id);
    /**
     * Creates a new team.
     *
     * @param teamDTO the data transfer object containing team details
     * @return an APIResponse confirming the creation of the team
     */
    APIResponse createTeam(TeamDTO teamDTO);
    /**
     * Finds teams that have no agents or managers.
     *
     * @return an APIResponse containing a list of empty teams
     */
    APIResponse findEmptyTeams();
    /**
     * Assigns a manager to a team.
     *
     * @param teamId the ID of the team
     * @param managerId the ID of the manager
     * @return an APIResponse confirming the assignment
     */
    APIResponse assignManagerToTeam(Long teamId, Long managerId);
    /**
     * Assigns an agent to a team.
     *
     * @param teamId the ID of the team
     * @param agentId the ID of the agent
     * @return an APIResponse confirming the assignment
     */
    APIResponse assignAgentToTeam(Long teamId, Long agentId);
}
