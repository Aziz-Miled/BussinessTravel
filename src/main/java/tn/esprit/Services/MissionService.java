package tn.esprit.Services;

import tn.esprit.Entities.Mission;

import java.util.List;

public interface MissionService {

    Mission addMission (Mission mission);
    Mission updateMission (Mission mission);
    List<Mission> findAllMissions();
    Mission findMissionById(Long id);
    void deleteMission(Long id);

}
