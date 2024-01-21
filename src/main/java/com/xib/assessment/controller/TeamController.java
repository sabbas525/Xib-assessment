package com.xib.assessment.controller;

import com.xib.assessment.mapper.teammapper.TeamDTO;
import com.xib.assessment.service.teamservice.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/team-controller")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * Retrieves a list of all teams.
     *
     * @return a ResponseEntity containing the list of teams
     */
    @GetMapping("/teams")
    public ResponseEntity<?> getAllTeams() {
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    /**
     * Retrieves the details of a specific team by ID.
     *
     * @param id the ID of the team
     * @return a ResponseEntity containing the team's details
     */
    @GetMapping("/team/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findTeamById(id));
    }

    /**
     * Creates a new team with the specified details.
     *
     * @param teamDTO the team data transfer object containing the team's information
     * @return a ResponseEntity indicating the result of the operation
     */
    @PostMapping("/team")
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    /**
     * Finds and returns a list of teams that currently have no agents or managers.
     *
     * @return a ResponseEntity containing a list of empty teams
     */
    @GetMapping("/empty-teams")
    public ResponseEntity<?> findEmptyTeams() {
        return ResponseEntity.ok(teamService.findEmptyTeams());
    }

    /**
     * Assigns a manager to a specified team.
     *
     * @param teamId    the ID of the team
     * @param managerId the ID of the manager
     * @return a ResponseEntity indicating the result of the operation
     */
    @PutMapping("/assign-manager/{teamId}/manager/{managerId}")
    public ResponseEntity<?> assignManagerToTeam(@PathVariable Long teamId, @PathVariable Long managerId) {
        return ResponseEntity.ok(teamService.assignManagerToTeam(teamId, managerId));
    }

    /**
     * Assigns an agent to a specified team.
     *
     * @param teamId  the ID of the team
     * @param agentId the ID of the agent
     * @return a ResponseEntity indicating the result of the operation
     */
    @PutMapping("/assign-agent/{teamId}/agent")
    public ResponseEntity<?> assignAgentToTeam(@PathVariable Long teamId,
                                               @RequestParam("agentId") Long agentId) {
        return ResponseEntity.ok(teamService.assignAgentToTeam(teamId, agentId));
    }
}
