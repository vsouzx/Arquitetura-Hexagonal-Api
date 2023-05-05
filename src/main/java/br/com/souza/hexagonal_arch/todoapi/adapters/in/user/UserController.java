package br.com.souza.hexagonal_arch.todoapi.adapters.in.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.LoginRequest;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.UserRequest;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.InsertUserInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserAuthenticationInputPort;
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
    private final UserAuthenticationInputPort userAuthenticationInputPort;

    public UserController(InsertUserInputPort insertUserInputPort,
                          UserAuthenticationInputPort userAuthenticationInputPort) {
        this.insertUserInputPort = insertUserInputPort;
        this.userAuthenticationInputPort = userAuthenticationInputPort;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequest userRequest) throws Exception{
        User user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword());
        insertUserInputPort.insertUser(user, userRequest.getZipCode());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationTokenResponse> auth(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
        return new ResponseEntity<>(userAuthenticationInputPort.authenticate(loginRequest), HttpStatus.OK);
    }

}
