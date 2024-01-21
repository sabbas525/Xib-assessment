package com.xib.assessment.mapper.managermapper;

import com.xib.assessment.mapper.teammapper.TeamMapper;
import com.xib.assessment.model.Manager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Manager entity and ManagerDTO.
 * Utilizes TeamMapper for nested team conversions.
 */
public class ManagerMapper {
    // Converts from ManagerDTO to Manager entity
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
    // Converts from Manager entity to ManagerDTO
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
    // Converts a list of Manager entities to a list of ManagerDTOs
    public static List<ManagerDTO> toManagerDTOList(List<Manager> managerList) {
        return managerList.stream().map(ManagerMapper::toManagerDTO).collect(Collectors.toList());
    }
    // Converts a list of ManagerDTOs to a list of Manager entities
    public static List<Manager> toManagerList(List<ManagerDTO> managerDTOList) {
        return managerDTOList.stream().map(ManagerMapper::toManager).collect(Collectors.toList());
    }
}
