package br.com.souza.hexagonal_arch.todoapi.adapters.in.consumer;

import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.UserMailConfirmationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableKafka
public class ReceiveMailForConfirmationConsumer {

    private final UserMailConfirmationOutputPort userMailConfirmationOutputPort;
    private static final String TOPIC = "mail-confirmation";
    private static final String GROUP = "souza";

    public ReceiveMailForConfirmationConsumer(UserMailConfirmationOutputPort userMailConfirmationOutputPort) {
        this.userMailConfirmationOutputPort = userMailConfirmationOutputPort;
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void receiveEmail(String email) throws Exception {
        log.info("Consumer: e-mail recebido: {} ", email);
        if(email != null || !email.isBlank()){
            userMailConfirmationOutputPort.sendMailConfirmation(email);
        }
    }
}
