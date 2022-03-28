package tn.esprit.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.esprit.Entities.TravelProgram;
import tn.esprit.Services.MissionService;
import tn.esprit.Services.TravelProgramService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class TravelProgramController {
	
	@Autowired
    private TravelProgramService travelProgramService;

    public TravelProgramController(TravelProgramService travelProgramService){
        this.travelProgramService = travelProgramService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TravelProgram>> getAllTravelPrograms (){
        travelProgramService.addTravelProgram();
        List<TravelProgram> prgs = travelProgramService.findAllTravelPrograms();
        return new ResponseEntity<>(prgs, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<List<TravelProgram>> getAllValidatedTravelPrograms (){
        travelProgramService.ValidateTravelProgram();
        List<TravelProgram> prgs = travelProgramService.findAllTravelPrograms();
        return new ResponseEntity<>(prgs, HttpStatus.OK);
    }

}
