package com.xib.assessment;

import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Manager;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.repository.ManagerRepository;
import com.xib.assessment.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadTestData {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @PostConstruct
    @Transactional
    public void execute() {
        Manager manager1 = createManager("Nick", "Fury");
        Manager manager2 = createManager("Amanda", "Waller");

        Team team1 = createTeam("Marvel", Arrays.asList(manager1));
        Team team2 = createTeam("DC", Arrays.asList(manager2));

        createAgent("Bruce", "Banner", "1011125190081", team1);
        createAgent("Tony", "Stark", "6912115191083", team1);
        createAgent("Peter", "Parker", "7801115190084", team1);
        createAgent("Bruce", "Wayne", "6511185190085", team2);
        createAgent("Clark", "Kent", "5905115190086", team2);
    }

    private Manager createManager(String firstName, String lastName) {
        Manager m = new Manager();
        m.setFirstName(firstName);
        m.setLastName(lastName);
        return managerRepository.save(m);
    }

    private Team createTeam(String name, List<Manager> managers) {
        Team t = new Team();
        t.setName(name);
        t.setManagers(managers);
        return teamRepository.save(t);
    }

    private Agent createAgent(String firstName, String lastName, String idNumber, Team team) {
        Agent a = new Agent();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setIdNumber(idNumber);
        a.setTeam(team);
        return agentRepository.save(a);
    }

}
