package tn.esprit.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class TravelProgram {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String destination;
    private String startingPoint = "Aéroport de tunis-carthage";
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private Status travelProgramStatus = Status.BEING_PROCESSED;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "travelProgram")
    @JsonManagedReference(value = "travelProgram")
    private List<Mission> missions;
}  
