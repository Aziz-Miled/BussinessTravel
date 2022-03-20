package tn.esprit.Services;

import tn.esprit.Exceptions.MissionNotFoundException;
import tn.esprit.Entities.Mission;
import tn.esprit.Repositories.MissionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionServiceImpl implements MissionService {

    private final MissionRepo missionRepo;

    public Mission addMission (Mission mission) {
        return missionRepo.save(mission);
    }

    public Mission updateMission (Mission mission) {
        return missionRepo.save(mission);
    }

    public List<Mission> findAllMissions(){
        return missionRepo.findAll();
    }

    public Mission findMissionById(Long id){
        return missionRepo.findMissionById(id).
                orElseThrow(() -> new MissionNotFoundException("Mission avec l'id " + id + " non trouvée "));
    }

    public void deleteMission(Long id){
        missionRepo.deleteMissionById(id);
    }

}
