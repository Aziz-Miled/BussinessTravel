package tn.esprit.Repositories;

import tn.esprit.Entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRepo extends JpaRepository<Mission, Long> {
    Optional<Mission> findMissionById(Long id);
    void deleteMissionById(Long id);
}
