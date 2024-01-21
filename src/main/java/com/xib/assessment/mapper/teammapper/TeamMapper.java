package com.xib.assessment.mapper.teammapper;

import com.xib.assessment.mapper.agentmapper.AgentMapper;
import com.xib.assessment.mapper.managermapper.ManagerMapper;
import com.xib.assessment.model.Team;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Team entity and TeamDTO.
 * Utilizes ManagerMapper for nested manager conversions.
 */
public class TeamMapper {
    // Converts from TeamDTO to Team entity
    public static Team toTeam(TeamDTO teamDTO) {
        if (teamDTO == null) {
            return null;
        }
        return Team.builder()
                .id(teamDTO.getId())
                .name(teamDTO.getName())
                .managers(ManagerMapper.toManagerList(teamDTO.getManagerDTOs()))
                .agents(AgentMapper.toAgentList(teamDTO.getAgentDTOs()))
                .build();
    }

    // Converts from Team entity to TeamDTO
    public static TeamDTO toTeamDTO(Team team) {
        if (team == null) {
            return null;
        }
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .managerDTOs(ManagerMapper.toManagerDTOList(team.getManagers()))
                .agentDTOs(AgentMapper.toAgentDTOList(team.getAgents()))
                .build();
    }

    // Converts a list of Team entities to a list of TeamDTOs
    public static List<TeamDTO> toTeamDTOList(List<Team> teamList) {
        return teamList.stream().map(TeamMapper::toTeamDTO).collect(Collectors.toList());
    }

    // Converts a list of TeamDTOs to a list of Team entities
    public static List<Team> toTeamList(List<TeamDTO> teamDTOList) {
        return teamDTOList.stream().map(TeamMapper::toTeam).collect(Collectors.toList());
    }
}
