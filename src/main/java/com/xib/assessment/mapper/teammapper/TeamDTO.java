package com.xib.assessment.mapper.teammapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.mapper.managermapper.ManagerDTO;
import lombok.*;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Team.
 * Used to transfer team data between layers without exposing the domain model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
    private Long id;
    private String name;
    private List<ManagerDTO> managerDTOs;
    private List<AgentDTO> agentDTOs;
}
