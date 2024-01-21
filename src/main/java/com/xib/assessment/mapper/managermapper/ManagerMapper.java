package com.xib.assessment.mapper.managermapper;

import com.xib.assessment.mapper.teammapper.TeamMapper;
import com.xib.assessment.model.Manager;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerMapper {
    public static Manager toManager(ManagerDTO managerDTO) {
        if (managerDTO == null) {
            return null;
        }
        return Manager.builder()
                .id(managerDTO.getId())
                .firstName(managerDTO.getFirstName())
                .lastName(managerDTO.getLastName())
                .teams(TeamMapper.toTeamList(managerDTO.getTeamDTOs()))
                .build();
    }

    public static ManagerDTO toManagerDTO(Manager manager) {
        if (manager == null) {
            return null;
        }
        return ManagerDTO.builder()
                .id(manager.getId())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .teamDTOs(TeamMapper.toTeamDTOList(manager.getTeams()))
                .build();
    }

    public static List<ManagerDTO> toManagerDTOList(List<Manager> managerList) {
        return managerList.stream().map(ManagerMapper::toManagerDTO).collect(Collectors.toList());
    }

    public static List<Manager> toManagerList(List<ManagerDTO> managerDTOList) {
        return managerDTOList.stream().map(ManagerMapper::toManager).collect(Collectors.toList());
    }
}
