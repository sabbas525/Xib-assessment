package com.xib.assessment.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * Represents an Agent entity in the system.
 * Includes basic personal information and associations with Team and Manager.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Agent {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Manager manager;

}
