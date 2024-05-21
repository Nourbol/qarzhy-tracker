package kz.edu.astanait.qarzhytracker.service;

public interface EmailSender {

    void sendVerificationCode(String to, String verificationCode);

    void send(String to, String subject, String text);
}
