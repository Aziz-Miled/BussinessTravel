package tn.esprit.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String adress;
    private String email;
    private Long phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date invitationDate;
    @Enumerated(EnumType.STRING)
    private Status invitationStatus = Status.BEING_PROCESSED;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<MissionAffectation> missionAffectations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="company")
    private List<Mission> missions;
}
