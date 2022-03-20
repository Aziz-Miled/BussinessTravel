package tn.esprit.Controllers;

import tn.esprit.Entities.MissionAffectation;
import tn.esprit.Services.MissionAffectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missionAffectation")
@RequiredArgsConstructor
public class MissionAffectationController {

    private final MissionAffectationService missionAffectationService;


    @GetMapping("/all")
    public ResponseEntity<List<MissionAffectation>> getAllMissionAffectations (){
        List<MissionAffectation> missionAffectations = missionAffectationService.findAllMissionAffectations();
        return new ResponseEntity<>(missionAffectations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MissionAffectation> getMissionAffectationById (@PathVariable("id") Long id){
        MissionAffectation missionAffectation = missionAffectationService.findMissionAffectationById(id);
        return new ResponseEntity<>(missionAffectation, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MissionAffectation> addMissionAffectation(@RequestBody MissionAffectation missionAffectation){
        MissionAffectation newMissionAffectation = missionAffectationService.addMissionAffectation(missionAffectation);
        return new ResponseEntity<>(newMissionAffectation,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MissionAffectation> updateMissionAffectation(@RequestBody MissionAffectation missionAffectation){
        MissionAffectation updateMissionAffectation = missionAffectationService.updateMissionAffectation(missionAffectation);
        return new ResponseEntity<>(updateMissionAffectation,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMissionAffectation(@PathVariable("id") Long id){
        missionAffectationService.deleteMissionAffectation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
