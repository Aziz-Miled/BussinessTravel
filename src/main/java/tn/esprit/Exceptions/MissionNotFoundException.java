package tn.esprit.Exceptions;

public class MissionNotFoundException extends RuntimeException{
    public MissionNotFoundException(String message) {
        super(message);
    }
}
