package tn.esprit.Services;


public interface EmailSenderService {

    public void sendEmail(String toEmail, String subject, String body);

}
