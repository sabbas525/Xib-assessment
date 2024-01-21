package com.xib.assessment.mapper.agentmapper;

import com.xib.assessment.mapper.managermapper.ManagerMapper;
import com.xib.assessment.mapper.teammapper.TeamMapper;
import com.xib.assessment.model.Agent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Agent entity and AgentDTO.
 * Utilizes ManagerMapper and TeamMapper for nested conversions.
 */
public class AgentMapper {
    // Converts from AgentDTO to Agent entity
    public static Agent toAgent(AgentDTO agentDTO) {
        if (agentDTO == null) {
            return null;
        }
        return Agent.builder()
                .id(agentDTO.getId())
                .firstName(agentDTO.getFirstName())
                .lastName(agentDTO.getLastName())
                .idNumber(agentDTO.getIdNumber())
                .team(TeamMapper.toTeam(agentDTO.getTeamDTO()))
                .manager(ManagerMapper.toManager(agentDTO.getManagerDTO()))
                .build();
    }

    // Converts from Agent entity to AgentDTO
    public static AgentDTO toAgentDTO(Agent agent) {
        if (agent == null) {
            return null;
        }
        return AgentDTO.builder()
                .id(agent.getId())
                .firstName(agent.getFirstName())
                .lastName(agent.getLastName())
                .idNumber(agent.getIdNumber())
                .teamDTO(TeamMapper.toTeamDTO(agent.getTeam()))
                .managerDTO(ManagerMapper.toManagerDTO(agent.getManager()))
                .build();
    }

    // Converts from Agent entity to AgentDTO (for pagination purposes)
    public static AgentDTO toAgentDTOPagination(Agent agent) {
        if (agent == null) {
            return null;
        }
        return AgentDTO.builder()
                .id(agent.getId())
                .firstName(agent.getFirstName())
                .lastName(agent.getLastName())
                .teamDTO(TeamMapper.toTeamDTO(agent.getTeam()))
                .managerDTO(ManagerMapper.toManagerDTO(agent.getManager()))
                .build();
    }

    // Updates an existing Agent entity with new data
    public static Agent toUpdateAgent(Agent agent, Agent updateAgent) {
        agent.setIdNumber(updateAgent.getIdNumber() == null ? agent.getIdNumber() : updateAgent.getIdNumber());
        agent.setFirstName(updateAgent.getFirstName() == null ? agent.getFirstName() : updateAgent.getFirstName());
        agent.setLastName(updateAgent.getLastName() == null ? agent.getLastName() : updateAgent.getLastName());
        agent.setTeam(updateAgent.getTeam() == null ? agent.getTeam() : updateAgent.getTeam());
        agent.setManager(updateAgent.getManager() == null ? agent.getManager() : updateAgent.getManager());
        return agent;
    }

    // Converts a list of Agent entities to a list of AgentDTOs
    public static List<AgentDTO> toAgentDTOList(List<Agent> agentList) {
        return agentList.stream().map(AgentMapper::toAgentDTO).collect(Collectors.toList());
    }

    // Converts a list of Agent entities to a list of AgentDTOs (for pagination)
    public static List<AgentDTO> toAgentDTOPaginationList(List<Agent> agentList) {
        return agentList.stream().map(AgentMapper::toAgentDTOPagination).collect(Collectors.toList());
    }

    // Converts a list of AgentDTOs to a list of Agent entities
    public static List<Agent> toAgentList(List<AgentDTO> agentDTOList) {
        return agentDTOList.stream().map(AgentMapper::toAgent).collect(Collectors.toList());
    }
}
