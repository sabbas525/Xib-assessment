package com.xib.assessment.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
/**
 * Represents a Manager entity in the system.
 * Contains basic personal information and a list of managed Teams.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Manager {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "manager_team",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;

}
