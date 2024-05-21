package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.exception.FailedEmailSendingException;
import kz.edu.astanait.qarzhytracker.service.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private static final String VERIFICATION_CODE_EMAIL_TEMPLATE = """
        <!doctype html>
        <html>
            <head>
                <meta name="viewport" content="width=device-width" />
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            </head>
            <body>
                <p>Hi,</p>
                <p>Thanks for signing up for a Qazrzhy Tracker account. We're excited to have you on board!</p>
                <p>Here is your verification code: %s</p>
                <p>Thanks,</p>
                <p>The Qarzhy Tracker Team</p>
            </body>
        </html>
        """;

    private static final String VERIFICATION_CODE_EMAIL_SUBJECT = "Verification code for your Qazhy Tracker account!";

    private final JavaMailSender emailSender;
    private final String senderEmail;

    public EmailSenderImpl(final JavaMailSender emailSender,
                           final @Value("${spring.mail.username}") String senderEmail) {
        this.emailSender = emailSender;
        this.senderEmail = senderEmail;
    }

    @Override
    public void sendVerificationCode(final String to, final String verificationCode) {
        send(to, VERIFICATION_CODE_EMAIL_SUBJECT, VERIFICATION_CODE_EMAIL_TEMPLATE.formatted(verificationCode));
    }

    @Override
    public void send(final String to, final String subject, final String text) {
        try {
            var message = emailSender.createMimeMessage();
            message.setSubject(subject);
            var helper  = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (final Exception exception) {
            throw new FailedEmailSendingException("There is some troubles with sending emails");
        }
    }
}
