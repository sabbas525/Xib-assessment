package com.xib.assessment.service.teamservice;


import com.xib.assessment.constants.AppConstants;
import com.xib.assessment.mapper.teammapper.TeamDTO;
import com.xib.assessment.mapper.teammapper.TeamMapper;
import com.xib.assessment.model.APIResponse;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Manager;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.repository.ManagerRepository;
import com.xib.assessment.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private AgentRepository agentRepository;

    @Override
    public APIResponse findAllTeams() {
        List<TeamDTO> teams = TeamMapper.toTeamDTOList(teamRepository.findAll());
        return buildSuccessResponse(teams);
    }

    @Override
    public APIResponse findTeamById(Long id) {
        return teamRepository.findById(id)
                .map(team -> buildSuccessResponse(TeamMapper.toTeamDTO(team)))
                .orElse(buildErrorResponse(AppConstants.VARIABLES.TEAM + AppConstants.VARIABLES.BLANK_SPACE + AppConstants.HTTP_MESSAGES.NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public APIResponse createTeam(TeamDTO teamDTO) {
        try {
            validateTeamManagers(teamDTO);
            Team team = TeamMapper.toTeam(teamDTO);
            team = teamRepository.save(team);
            return buildSuccessResponse(TeamMapper.toTeamDTO(team));
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public APIResponse findEmptyTeams() {
        List<Team> emptyTeams = teamRepository.findByAgentsIsEmptyAndManagersIsEmpty();
        return buildSuccessResponse(TeamMapper.toTeamDTOList(emptyTeams));
    }

    @Override
    public APIResponse assignManagerToTeam(Long teamId, Long managerId) {
        try {
            Team team = getTeam(teamId);
            Manager manager = getManager(managerId);
            addManagerToTeam(team, manager);
            return buildSuccessResponse(null);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public APIResponse assignAgentToTeam(Long teamId, Long agentId) {
        try {
            Team team = getTeam(teamId);
            Agent agent = getAgent(agentId);
            validateAgentManager(team, agent);
            agent.setTeam(team);
            agentRepository.save(agent);
            return buildSuccessResponse(null);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateTeamManagers(TeamDTO teamDTO) {
        if (teamDTO.getManagerDTOs() != null && teamDTO.getManagerDTOs().size() > 2) {
            throw new IllegalArgumentException(AppConstants.EXCEPTION_MESSAGES.TEAM_MAX_MANAGER_EXCEPTION);
        }
    }

    private void addManagerToTeam(Team team, Manager manager) {
        if (team.getManagers().size() >= 2) {
            throw new IllegalArgumentException(AppConstants.EXCEPTION_MESSAGES.TEAM_MAX_MANAGER_EXCEPTION);
        }
        team.getManagers().add(manager);
        teamRepository.save(team);
    }

    private void validateAgentManager(Team team, Agent agent) {
        if (!team.getManagers().contains(agent.getManager())) {
            throw new IllegalArgumentException(AppConstants.EXCEPTION_MESSAGES.AGENT_TEAM_MANAGER_VALIDATION_MESSAGE_2);
        }
    }

    private Team getTeam(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException(AppConstants.HTTP_MESSAGES.NOT_FOUND));
    }

    private Manager getManager(Long managerId) {
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException(AppConstants.HTTP_MESSAGES.NOT_FOUND));
    }

    private Agent getAgent(Long agentId) {
        return agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException(AppConstants.HTTP_MESSAGES.NOT_FOUND));
    }

    private APIResponse buildSuccessResponse(Object data) {
        return APIResponse.builder().code(HttpStatus.OK.value()).message(AppConstants.HTTP_MESSAGES.SUCCESS).data(data).build();
    }

    private APIResponse buildErrorResponse(String errorMessage, HttpStatus status) {
        return APIResponse.builder().code(status.value()).error(errorMessage).build();
    }

}
