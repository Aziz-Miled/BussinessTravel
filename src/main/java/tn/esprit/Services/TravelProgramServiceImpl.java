package tn.esprit.Services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.Entities.Mission;
import tn.esprit.Entities.Status;
import tn.esprit.Entities.TravelProgram;
import tn.esprit.Repositories.TravelProgramRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelProgramServiceImpl implements TravelProgramService {

	private final TravelProgramRepo travelProgramRepo;

    @Autowired
    private MissionService missionService;

    @Autowired
    EmailSenderService emailSenderService;

    public List<TravelProgram> matchingFunction() {
        List<TravelProgram> setPrg = new ArrayList<>();
        List<Mission> checkList = new ArrayList<>();
        boolean test = true;
        Date minDate;
        Date maxDate;
        Set<Mission> missSet = new HashSet<>();
        List<Mission> missList;
        int cpt = 0;
        List<Mission> m1 = new ArrayList<>();
        List<Mission> list = missionService.findAllMissions();
        List<Mission> listPrg = new ArrayList<>();

        for (TravelProgram travelProgram : findAllTravelPrograms()){
            for (Mission mission : travelProgram.getMissions()){
                listPrg.add(mission);
            }
        }
        for (Mission mission : listPrg){
            if (list.contains(mission)){
                list.remove(mission);
            }
        }

        for (Mission mOne : list) {
            if ( checkList.contains(mOne)){
                continue;
            }
            else{
                for (Mission mTwo : list) {
                    if (mOne.equals(mTwo)){
                        m1.add(mOne);
                        checkList.add(mOne);
                    }
                    else if (mOne.getDestination().equals(mTwo.getDestination())) {
                        m1.add(mTwo);
                        checkList.add(mTwo);
                    }
                }

                // traitement matching de voyage

                if (m1.size()==1){
                    TravelProgram prg = new TravelProgram();
                    prg.setEndDate(m1.get(0).getEndDate());
                    prg.setStartDate(m1.get(0).getStartDate());
                    prg.setDestination(m1.get(0).getDestination());
                    prg.setMissions(new ArrayList<>(m1));
                    setPrg.add(prg);
                    m1.clear();
                    continue;
                }

                Collections.sort(m1);

                minDate = m1.stream().findFirst().get().getStartDate();
                maxDate = m1.stream().findFirst().get().getEndDate();

                for (int l = 0; l < m1.size() - 1 ; l++) {

                    missSet.add(m1.get(l));

                    if (m1.get(l + 1).getStartDate().compareTo(minDate) < 0) {
                        minDate = m1.get(l + 1).getStartDate();
                    }

                    if (m1.get(l + 1).getEndDate().compareTo(maxDate) > 0) {
                        maxDate = m1.get(l + 1).getEndDate();
                    }

                    if ((m1.get(l).getEndDate().compareTo(m1.get(l + 1).getStartDate()) > 0)
                            || maxDate.compareTo(m1.get(l + 1).getStartDate()) > 0) {
                        missSet.add(m1.get(l));
                        missSet.add(m1.get(l + 1));
                        cpt++;

                    } else {
                        test = false;
                    }

                    if ( test==false && cpt == 0){

                        TravelProgram prg = new TravelProgram();
                        prg.setEndDate(m1.get(l).getEndDate());
                        prg.setStartDate(m1.get(l).getStartDate());
                        prg.setDestination(m1.get(l).getDestination());
                        missList = new ArrayList<>(missSet);
                        prg.setMissions(missList);
                        setPrg.add(prg);
                        missSet.clear();
                        missList.clear();
                        test = true;
                        cpt = 0;


                        if (l == m1.size()-2){
                            TravelProgram tPrg = new TravelProgram();
                            tPrg.setEndDate(m1.get(l+1).getEndDate());
                            tPrg.setStartDate(m1.get(l+1).getStartDate());
                            tPrg.setDestination(m1.get(l+1).getDestination());
                            missList.add(m1.get(l+1));
                            prg.setMissions(new ArrayList<>(missList));
                            setPrg.add(tPrg);
                            missSet.clear();
                            missList.clear();
                            test = true;
                            cpt = 0;

                        }
                    }
                    else if (test == false && cpt > 0) {

                        TravelProgram prg = new TravelProgram();
                        prg.setEndDate(maxDate);
                        prg.setStartDate(minDate);
                        prg.setDestination(missSet.stream().findFirst().get().getDestination());
                        missList = new ArrayList<>(missSet);
                        prg.setMissions(new ArrayList<>(missList));
                        setPrg.add(prg);
                        test = true;
                        cpt = 0;
                        missList.clear();
                        missSet.clear();


                        if (l == m1.size()-2){

                            TravelProgram tPrg = new TravelProgram();
                            tPrg.setEndDate(m1.get(l+1).getEndDate());
                            tPrg.setStartDate(m1.get(l+1).getStartDate());
                            tPrg.setDestination(m1.get(l+1).getDestination());
                            missList.add(m1.get(l+1));
                            tPrg.setMissions(new ArrayList<>(missList));
                            setPrg.add(tPrg);
                            missSet.clear();
                            missList.clear();
                            test = true;
                            cpt = 0;

                        }

                    }
                    else if ( test ==  true && l == m1.size()-2 ){
                        TravelProgram prg = new TravelProgram();
                        prg.setEndDate(maxDate);
                        prg.setStartDate(minDate);
                        prg.setDestination(missSet.stream().findFirst().get().getDestination());
                        missList = new ArrayList<>(missSet);
                        prg.setMissions(new ArrayList<>(missList));
                        setPrg.add(prg);
                        test = true;
                        cpt = 0;
                        missList.clear();
                        missSet.clear();

                    }

                }

                m1.clear();

            }

        }

        return setPrg;

    }

    public void updateMissionsOfTravelPrograms(){
        for (TravelProgram travelProgram : findAllTravelPrograms()){
            for (Mission m : travelProgram.getMissions()){
                m.setTravelProgram(travelProgram);
                missionService.updateMission(m);
            }
        }
    }

    public void addTravelProgram(){
        for ( TravelProgram prg : matchingFunction()){
            travelProgramRepo.save(prg);
            updateMissionsOfTravelPrograms();
        }
    }

    public List<TravelProgram> findAllTravelPrograms(){
        return travelProgramRepo.findAll();
    }

    public TravelProgram updateTravelProgram(TravelProgram travelProgram){
        return travelProgramRepo.save(travelProgram);
    }

    public void ValidateTravelProgram(){
        int i = 0;
        int max = 0;
        List<Mission> validMissions = new ArrayList<>();
        List<Mission> invalidMissions = new ArrayList<>();
        for (TravelProgram travelProgram : findAllTravelPrograms()){
            max = travelProgram.getMissions().size();
            if ( travelProgram.getTravelProgramStatus() == Status.BEING_PROCESSED){
                for(Mission mission : travelProgram.getMissions()){
                    if (mission.getMissionStatus()==Status.VALID && i<=3 && i <= max ){
                        i++;
                        validMissions.add(mission);
                    }
                    else{
                        invalidMissions.add(mission);
                    }
                }
                if( i == max || i == 3 ){
                    travelProgram.setTravelProgramStatus(Status.VALID);
                    updateTravelProgram(travelProgram);
                    for ( Mission m : validMissions ) {
                        emailSenderService.sendEmail(m.getCompany().getEmail(),
                                "Reception of validation of your travel program",
                                "Dear "+m.getCompany().getFirstName()+",\n" +
                                        "The travel program that you have validated is ready and set to go !\n"+
                                        "Please refer to the main application interface for more informations \n"+
                                        "Thank you.\n"+
                                        "\n"+
                                        "Kind regards,\n"+
                                        "The Business Travel Team"
                        );
                    }
                    for ( Mission m : invalidMissions ) {
                        emailSenderService.sendEmail(m.getCompany().getEmail(),
                                "Failure of validation of your travel program",
                                "Dear "+m.getCompany().getFirstName()+",\n" +
                                        "The travel program that you have been matched to has already been finalized by other companies, thus you have been moved out of the travel program\n"+
                                        "But no worries ! You can always find another match on our app !\n"+
                                        "Please refer to the main application interface for more informations \n"+
                                        "Thank you.\n"+
                                        "\n"+
                                        "Kind regards,\n"+
                                        "The Business Travel Team"
                        );
                    }
                }

                for(Mission mission : invalidMissions){
                    if (travelProgram.getMissions().contains(mission)){
                        travelProgram.getMissions().remove(mission);
                        mission.setTravelProgram(null);
                        missionService.updateMission(mission);
                    }
                }
                updateTravelProgram(travelProgram);

                validMissions.clear();
                invalidMissions.clear();
                i=0;
            }
        }
    }

}
