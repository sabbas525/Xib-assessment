package com.xib.assessment.controller;


import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.service.agentservice.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping
    public ResponseEntity<?> getAllAgents() {
        return ResponseEntity.ok(agentService.findAllAgents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok(agentService.findAgentById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAgent(@RequestBody AgentDTO agentDTO) {
        return ResponseEntity.ok(agentService.createAgent(agentDTO));
    }
}
