package com.xib.assessment.mapper.agentmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * Data Transfer Object (DTO) for agent pagination.
 * Contains information about total pages, total elements, and a list of agents.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentPaginationDTO {
    private Integer totalPages;
    private Long totalElements;
    private List<AgentDTO> data;
}
