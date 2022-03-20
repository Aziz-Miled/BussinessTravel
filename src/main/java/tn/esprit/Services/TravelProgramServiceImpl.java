package tn.esprit.Services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Repositories.TravelProgramRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelProgramServiceImpl implements TravelProgramService{

    private final TravelProgramRepo travelProgramRepo;




}
