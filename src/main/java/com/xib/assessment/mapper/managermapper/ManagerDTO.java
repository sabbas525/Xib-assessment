package com.xib.assessment.mapper.managermapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xib.assessment.mapper.teammapper.TeamDTO;
import lombok.*;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Manager.
 * Used to transfer manager data between layers without exposing the domain model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<TeamDTO> teamDTOs;
}
