package tn.esprit.Entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class MissionAffectationPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idMission;
    private Long idUser;

}
