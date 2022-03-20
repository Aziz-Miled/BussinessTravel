package tn.esprit.Services;

import tn.esprit.Entities.MissionAffectation;

import java.util.List;

public interface MissionAffectationService {

    MissionAffectation addMissionAffectation(MissionAffectation missionAffectation);
    MissionAffectation updateMissionAffectation(MissionAffectation missionAffectation);
    List<MissionAffectation> findAllMissionAffectations();
    MissionAffectation findMissionAffectationById(Long id);
    void deleteMissionAffectation (Long id);
}
