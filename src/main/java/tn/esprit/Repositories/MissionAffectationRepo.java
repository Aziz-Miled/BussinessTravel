package tn.esprit.Repositories;

import tn.esprit.Entities.MissionAffectation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionAffectationRepo extends JpaRepository<MissionAffectation,Long> {
    Optional<MissionAffectation> findMissionAffectationById(Long id);
    void deleteMissionAffectationById(Long id);
}
