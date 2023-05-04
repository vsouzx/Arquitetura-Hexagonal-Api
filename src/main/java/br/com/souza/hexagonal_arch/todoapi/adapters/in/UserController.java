package br.com.souza.hexagonal_arch.todoapi.adapters.in;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.dto.UserRequest;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.InsertUserInputPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todo/v1/user")
public class UserController {

    private final InsertUserInputPort insertUserInputPort;

    public UserController(InsertUserInputPort insertUserInputPort) {
        this.insertUserInputPort = insertUserInputPort;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequest userRequest) throws Exception{
        User user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail());
        insertUserInputPort.insertUser(user, userRequest.getZipCode());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
