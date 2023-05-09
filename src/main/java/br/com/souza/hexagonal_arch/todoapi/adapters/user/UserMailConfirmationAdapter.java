package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.repository.UserCollectionRepository;
import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserMailConfirmationOutputPort;
import java.util.Optional;
import java.util.Random;
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
    private final UserCollectionRepository userCollectionRepository;

    public UserMailConfirmationAdapter(JavaMailSender mailSender,
                                       UserCollectionRepository userCollectionRepository) {
        this.mailSender = mailSender;
        this.userCollectionRepository = userCollectionRepository;
    }

    @Override
    public void sendMailConfirmation(String email){
        final String confirmationCode = generateRandomCode();

        Optional<UserCollection> user = userCollectionRepository.findByEmail(email);
        if(user.isPresent()){
            user.get().setConfirmationCode(confirmationCode);
            userCollectionRepository.save(user.get());

            var message = new SimpleMailMessage();
            message.setTo(user.get().getEmail());
            message.setSubject(SUBJECT);
            message.setText(String.format(TEXT, user.get().getFirstName(), confirmationCode));

            mailSender.send(message);
        }
    }

    private String generateRandomCode(){
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(999999);
        return String.format("%06d", randomNumber);
    }
}
