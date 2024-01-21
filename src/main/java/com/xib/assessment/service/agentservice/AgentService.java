package com.xib.assessment.service.agentservice;

import com.xib.assessment.mapper.agentmapper.AgentDTO;
import com.xib.assessment.model.APIResponse;

public interface AgentService {
    APIResponse findAllAgents();
    APIResponse findAgentById(Long id);
    APIResponse createAgent(AgentDTO agentDTO);
}
