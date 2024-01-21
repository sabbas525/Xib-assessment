package com.xib.assessment.service.agentservice;

import com.xib.assessment.constants.AppConstants;
import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.mapper.agentmapper.AgentMapper;
import com.xib.assessment.mapper.agentmapper.AgentPaginationDTO;
import com.xib.assessment.model.APIResponse;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Manager;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.repository.ManagerRepository;
import com.xib.assessment.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public APIResponse findAllAgents(Integer pageNumber, Integer pageSize) {
        return pageNumber != null && pageSize != null ?
                paginateAgents(pageNumber, pageSize) :
                listAllAgents();
    }

    private APIResponse paginateAgents(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Agent> agentPage = agentRepository.findAll(pageable);
        AgentPaginationDTO result = createAgentPaginationDTO(agentPage);
        return buildSuccessResponse(result);
    }

    private AgentPaginationDTO createAgentPaginationDTO(Page<Agent> agentPage) {
        AgentPaginationDTO result = new AgentPaginationDTO();
        result.setData(AgentMapper.toAgentDTOPaginationList(agentPage.getContent()));
        result.setTotalPages(agentPage.getTotalPages());
        result.setTotalElements(agentPage.getTotalElements());
        return result;
    }

    private APIResponse listAllAgents() {
        List<AgentDTO> agents = AgentMapper.toAgentDTOList(agentRepository.findAll());
        return buildSuccessResponse(agents);
    }

    @Override
    public APIResponse findAgentById(Long id) {
        return agentRepository.findById(id)
                .map(agent -> buildSuccessResponse(AgentMapper.toAgentDTO(agent)))
                .orElse(buildErrorResponse(AppConstants.VARIABLES.AGENT + AppConstants.VARIABLES.BLANK_SPACE + AppConstants.HTTP_MESSAGES.NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public APIResponse createAgent(AgentDTO agentDTO) {
        if (agentDTO.getId() != null && agentRepository.existsById(agentDTO.getId())) {
            return buildErrorResponse(AppConstants.EXCEPTION_MESSAGES.AGENT_ALREADY_ASSIGNED_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return handleAgentCreation(agentDTO);
    }

    private APIResponse handleAgentCreation(AgentDTO agentDTO) {
        try {
            validateAgentTeamAndManager(agentDTO);
            Agent agent = AgentMapper.toAgent(agentDTO);
            agent = agentRepository.save(agent);
            return buildSuccessResponse(AgentMapper.toAgentDTO(agent));
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateAgentTeamAndManager(AgentDTO agentDTO) {
        Team team = getTeam(agentDTO.getTeamDTO().getId());
        Manager manager = getManager(agentDTO.getManagerDTO().getId());
        if (!team.getManagers().contains(manager)) {
            throw new IllegalArgumentException(AppConstants.EXCEPTION_MESSAGES.AGENT_TEAM_MANAGER_VALIDATION_MESSAGE);
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

    private APIResponse buildSuccessResponse(Object data) {
        return APIResponse.builder().code(HttpStatus.OK.value()).message(AppConstants.HTTP_MESSAGES.SUCCESS).data(data).build();
    }

    private APIResponse buildErrorResponse(String errorMessage, HttpStatus status) {
        return APIResponse.builder().code(status.value()).error(errorMessage).build();
    }
}

