package com.xib.assessment.service.teamservice;


import com.xib.assessment.mapper.teammapper.TeamDTO;
import com.xib.assessment.model.APIResponse;

public interface TeamService {
    APIResponse findAllTeams();
    APIResponse findTeamById(Long id);
    APIResponse createTeam(TeamDTO teamDTO);
}
