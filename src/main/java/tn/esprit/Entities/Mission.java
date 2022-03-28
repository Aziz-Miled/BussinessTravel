package tn.esprit.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.lang.Nullable;

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
    @Enumerated(EnumType.STRING)
    private Status missionStatus = Status.BEING_PROCESSED;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miss")
    @JsonManagedReference(value = "miss")
    private List<MissionAffectation> missionAffectations;
    @ManyToOne
    @JsonBackReference(value = "company")
    private User company;
    @Nullable
    @ManyToOne
    @JsonBackReference(value = "travelProgram")
    private TravelProgram travelProgram;

    @Override
    public int compareTo(Mission mission) {
        return getStartDate().compareTo(mission.getStartDate());
    }

}
