package com.xib.assessment.mapper.teammapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xib.assessment.mapper.managermapper.ManagerDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
    private Long id;
    private String name;
    private List<ManagerDTO> managerDTOs;;
}
