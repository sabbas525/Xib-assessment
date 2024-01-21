package com.xib.assessment.service.teamservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xib.assessment.constants.AppConstants;
import com.xib.assessment.mapper.teammapper.TeamDTO;
import com.xib.assessment.mapper.teammapper.TeamMapper;
import com.xib.assessment.model.APIResponse;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public APIResponse findAllTeams() {
        try {
            return APIResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(AppConstants.HTTP_MESSAGES.SUCCESS)
                    .data(TeamMapper.toTeamDTOList(teamRepository.findAll()))
                    .build();
        } catch (Exception e) {
            return APIResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(e.getMessage()).build();
        }
    }

    @Override
    public APIResponse findTeamById(Long id) {
        try {
            return teamRepository.findById(id)
                    .map(team -> APIResponse.builder().code(HttpStatus.OK.value()).message(AppConstants.HTTP_MESSAGES.SUCCESS).data(TeamMapper.toTeamDTO(team)).build())
                    .orElse(APIResponse.builder().code(HttpStatus.NOT_FOUND.value()).error(AppConstants.VARIABLES.TEAM + AppConstants.VARIABLES.BLANK_SPACE + AppConstants.HTTP_MESSAGES.NOT_FOUND).build());
        } catch (Exception e) {
            return APIResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(e.getMessage()).build();
        }
    }

    @Override
    public APIResponse createTeam(TeamDTO teamDTO) {
        try {
            // Check if the team has at most 2 managers
            if (teamDTO.getManagerDTOs() != null && teamDTO.getManagerDTOs().size() > 2) {
                return APIResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .error(AppConstants.VARIABLES.TEAM_MAX_ASSIGNED_MESSAGE)
                        .build();
            }
            Team team = TeamMapper.toTeam(teamDTO);
            team = teamRepository.save(team);
            return APIResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(AppConstants.HTTP_MESSAGES.SUCCESS)
                    .data(TeamMapper.toTeamDTO(team))
                    .build();
        } catch (Exception e) {
            return APIResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(e.getMessage()).build();
        }
    }

}
