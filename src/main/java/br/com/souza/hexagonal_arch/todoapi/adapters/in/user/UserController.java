package br.com.souza.hexagonal_arch.todoapi.adapters.in.user;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.AuthenticationTokenResponse;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.ConfirmationCodeRequest;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.LoginRequest;
import br.com.souza.hexagonal_arch.todoapi.adapters.in.user.dto.UserRequest;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.User;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.InsertUserInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserAuthenticationInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.user.UserConfirmSecurityCodeInputPort;
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
    private final UserConfirmSecurityCodeInputPort userConfirmSecurityCodeInputPort;

    public UserController(InsertUserInputPort insertUserInputPort,
                          UserAuthenticationInputPort userAuthenticationInputPort,
                          UserConfirmSecurityCodeInputPort userConfirmSecurityCodeInputPort) {
        this.insertUserInputPort = insertUserInputPort;
        this.userAuthenticationInputPort = userAuthenticationInputPort;
        this.userConfirmSecurityCodeInputPort = userConfirmSecurityCodeInputPort;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest) throws Exception{
        insertUserInputPort.insertUser(new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword()), userRequest.getZipCode());
        return new ResponseEntity<>("A confirmation code was sent to your e-mail. Please, check it out and confirm by the '/todo/v1/user/codeconfirmation' endpoint.", HttpStatus.CREATED);
    }

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationTokenResponse> auth(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
        return new ResponseEntity<>(userAuthenticationInputPort.authenticate(loginRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/codeconfirmation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirm(@Valid @RequestBody ConfirmationCodeRequest confirmationCodeRequest) throws Exception{
        userConfirmSecurityCodeInputPort.confirm(confirmationCodeRequest.getConfirmationCode(), confirmationCodeRequest.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
