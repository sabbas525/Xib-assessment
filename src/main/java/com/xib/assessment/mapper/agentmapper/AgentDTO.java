package com.xib.assessment.mapper.agentmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xib.assessment.mapper.managermapper.ManagerDTO;
import com.xib.assessment.mapper.teammapper.TeamDTO;
import lombok.*;

/**
 * Data Transfer Object (DTO) for Agent.
 * Used to transfer agent data between layers without exposing the domain model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    private TeamDTO teamDTO;
    private ManagerDTO managerDTO;
}
