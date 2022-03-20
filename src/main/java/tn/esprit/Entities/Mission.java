package tn.esprit.Entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mission implements Serializable, Comparable<Mission> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String destination;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String missionObject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miss")
    private List<MissionAffectation> missionAffectations;
    @ManyToOne
    private User company;
    @ManyToOne
    private TravelProgram travelProgram;

    @Override
    public int compareTo(Mission mission) {
        return getStartDate().compareTo(mission.getStartDate());
    }
}
