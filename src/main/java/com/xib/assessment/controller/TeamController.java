package com.xib.assessment.controller;

import com.xib.assessment.mapper.teammapper.TeamDTO;
import com.xib.assessment.service.teamservice.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findTeamById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }
}
