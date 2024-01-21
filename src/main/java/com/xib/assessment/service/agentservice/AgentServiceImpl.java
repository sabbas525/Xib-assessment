package com.xib.assessment.service.agentservice;

import com.xib.assessment.constants.AppConstants;
import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.mapper.agentmapper.AgentMapper;
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

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public APIResponse findAllAgents() {
        try {
            return APIResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(AppConstants.HTTP_MESSAGES.SUCCESS)
                    .data(AgentMapper.toAgentDTOList(agentRepository.findAll()))
                    .build();
        } catch (Exception e) {
            return APIResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(e.getMessage()).build();
        }
    }

    @Override
    public APIResponse findAgentById(Long id) {
        try {
            return agentRepository.findById(id)
                    .map(agent -> APIResponse.builder().code(HttpStatus.OK.value()).message(AppConstants.HTTP_MESSAGES.SUCCESS).data(AgentMapper.toAgentDTO(agent)).build())
                    .orElse(APIResponse.builder().code(HttpStatus.NOT_FOUND.value()).error(AppConstants.VARIABLES.AGENT + AppConstants.VARIABLES.BLANK_SPACE + AppConstants.HTTP_MESSAGES.NOT_FOUND).build());
        } catch (Exception e) {
            return APIResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(e.getMessage()).build();
        }
    }

    @Override
    public APIResponse createAgent(AgentDTO agentDTO) {
        try {
            // Check if the agent already exists
            if (agentDTO.getId() != null && agentRepository.existsById(agentDTO.getId())) {
                return APIResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .error(AppConstants.VARIABLES.AGENT_ALREADY_ASSIGNED_MESSAGE)
                        .build();
            }

            // Retrieve and validate the team and manager
            Team team = teamRepository.findById(agentDTO.getTeamDTO().getId())
                    .orElseThrow(() -> new IllegalArgumentException(AppConstants.HTTP_MESSAGES.NOT_FOUND));
            Manager manager = managerRepository.findById(agentDTO.getManagerDTO().getId())
                    .orElseThrow(() -> new IllegalArgumentException(AppConstants.HTTP_MESSAGES.NOT_FOUND));

            // Check if the team is managed by the same manager as the agent's manager
            if (!team.getManagers().contains(manager)) {
                return APIResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .error("Agent can only be assigned to a team managed by their manager")
                        .build();
            }

            // Save the agent
            Agent agent = AgentMapper.toAgent(agentDTO);
            agent = agentRepository.save(agent);
            return APIResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(AppConstants.HTTP_MESSAGES.SUCCESS)
                    .data(AgentMapper.toAgentDTO(agent))
                    .build();
        } catch (Exception e) {
            return APIResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(e.getMessage()).build();
        }
    }
}
