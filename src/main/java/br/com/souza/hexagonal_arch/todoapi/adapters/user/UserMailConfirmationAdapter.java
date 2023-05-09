package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserMailConfirmationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMailConfirmationAdapter implements UserMailConfirmationOutputPort {

    private final static String SUBJECT = "To-do API Confirmation Code";
    private final static String TEXT = "%s, your 6 digit confirmation code is: %s";
    private final JavaMailSender mailSender;

    public UserMailConfirmationAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMailConfirmation(String userName, String email, String confirmationCode){
        var message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject(SUBJECT);
        message.setText(String.format(TEXT, userName, confirmationCode));

        mailSender.send(message);
    }
}
