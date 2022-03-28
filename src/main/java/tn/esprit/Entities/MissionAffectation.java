package tn.esprit.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissionAffectation {
    @EmbeddedId
    private MissionAffectationPK id;
    @Temporal(TemporalType.DATE)
    private Date affectationDate;
    @Enumerated(EnumType.STRING)
    private Status missionAffectationStatus = Status.BEING_PROCESSED;
    @ManyToOne
    @JoinColumn(name = "idUser", insertable = false , updatable = false  )
    @JsonBackReference(value = "employee")
    private User employee;
    @ManyToOne
    @JoinColumn(name = "idMission", insertable = false , updatable = false)
    @JsonBackReference(value = "miss")
    private Mission miss;
}
