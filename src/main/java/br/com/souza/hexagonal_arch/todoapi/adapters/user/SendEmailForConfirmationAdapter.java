package br.com.souza.hexagonal_arch.todoapi.adapters.user;

import br.com.souza.hexagonal_arch.todoapi.application.ports.out.user.SendEmailForConfirmationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendEmailForConfirmationAdapter implements SendEmailForConfirmationOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "mail-confirmation";

    public SendEmailForConfirmationAdapter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String email){
        kafkaTemplate.send(TOPIC, email);
    }
}
