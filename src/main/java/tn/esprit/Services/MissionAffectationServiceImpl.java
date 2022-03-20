package tn.esprit.Services;


import tn.esprit.Exceptions.MissionAffectationNotFoundException;
import tn.esprit.Entities.MissionAffectation;
import tn.esprit.Repositories.MissionAffectationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionAffectationServiceImpl implements MissionAffectationService{

    private final MissionAffectationRepo missionAffectationRepo;

    public MissionAffectation addMissionAffectation(MissionAffectation missionAffectation){
        return missionAffectationRepo.save(missionAffectation);
    }

    public MissionAffectation updateMissionAffectation(MissionAffectation missionAffectation){
        return missionAffectationRepo.save(missionAffectation);
    }

    public List<MissionAffectation> findAllMissionAffectations(){
        return missionAffectationRepo.findAll();
    }

    public MissionAffectation findMissionAffectationById(Long id){
        return missionAffectationRepo.findMissionAffectationById(id).
                orElseThrow(() -> new MissionAffectationNotFoundException("Mission Affectation avec l'id " + id + " non trouvée "));
    }

    public void deleteMissionAffectation (Long id){
        missionAffectationRepo.deleteMissionAffectationById(id);
    }
}
