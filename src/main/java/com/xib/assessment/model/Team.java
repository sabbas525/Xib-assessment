package com.xib.assessment.model;

import com.xib.assessment.constants.AppConstants;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a Team entity in the system.
 * Includes team name and a list of associated Managers.
 * Enforces a constraint of having at most 2 managers per team.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "teams")
    private List<Manager> managers;

    // Ensure that there are at most 2 managers for a team
    public void setManagers(List<Manager> managers) {
        if (managers != null && managers.size() > 2) {
            throw new IllegalArgumentException(AppConstants.EXCEPTION_MESSAGES.TEAM_MAX_MANAGER_EXCEPTION);
        }
        this.managers = managers;
    }
    @OneToMany(mappedBy = "team")
    private List<Agent> agents;
}
