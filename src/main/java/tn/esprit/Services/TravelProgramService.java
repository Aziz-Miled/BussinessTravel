package tn.esprit.Services;

import java.util.List;

import tn.esprit.Entities.TravelProgram;

public interface TravelProgramService {
	
	public List<TravelProgram> matchingFunction();
    public void addTravelProgram();
	public List<TravelProgram> findAllTravelPrograms();
	public void ValidateTravelProgram();
	public TravelProgram updateTravelProgram(TravelProgram travelProgram);
	public void updateMissionsOfTravelPrograms();
	
}
