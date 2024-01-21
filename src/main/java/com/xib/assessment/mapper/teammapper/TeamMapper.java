package com.xib.assessment.mapper.teammapper;

import com.xib.assessment.mapper.managermapper.ManagerMapper;
import com.xib.assessment.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {
    public static Team toTeam(TeamDTO teamDTO) {
        if (teamDTO == null) {
            return null;
        }
        return Team.builder()
                .id(teamDTO.getId())
                .name(teamDTO.getName())
                .managers(ManagerMapper.toManagerList(teamDTO.getManagerDTOs()))
                .build();
    }

    public static TeamDTO toTeamDTO(Team team) {
        if (team == null) {
            return null;
        }
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .managerDTOs(ManagerMapper.toManagerDTOList(team.getManagers()))
                .build();
    }

    public static List<TeamDTO> toTeamDTOList(List<Team> teamList) {
        return teamList.stream().map(TeamMapper::toTeamDTO).collect(Collectors.toList());
    }

    public static List<Team> toTeamList(List<TeamDTO> teamDTOList) {
        return teamDTOList.stream().map(TeamMapper::toTeam).collect(Collectors.toList());
    }
}
