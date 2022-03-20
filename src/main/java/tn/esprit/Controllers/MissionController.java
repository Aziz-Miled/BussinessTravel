package tn.esprit.Controllers;


import tn.esprit.Entities.Mission;
import tn.esprit.Services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/all")
    public ResponseEntity<List<Mission>> getAllMissions (){
        List<Mission> missions = missionService.findAllMissions();
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Mission> getMissionById (@PathVariable("id") Long id){
        Mission miss = missionService.findMissionById(id);
        return new ResponseEntity<>(miss, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Mission> addMission(@RequestBody Mission mission){
        Mission newMission = missionService.addMission(mission);
        return new ResponseEntity<>(newMission,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Mission> updateMission(@RequestBody Mission mission){
        Mission updateMission = missionService.updateMission(mission);
        return new ResponseEntity<>(updateMission,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMission(@PathVariable("id") Long id){
        missionService.deleteMission(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
