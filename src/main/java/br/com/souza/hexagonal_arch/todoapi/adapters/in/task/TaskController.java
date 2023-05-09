package br.com.souza.hexagonal_arch.todoapi.adapters.in.task;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.task.dto.TaskRequest;
import br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model.UserCollection;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.FindAllTasksInputPort;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.InsertTaskInputPort;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todo/v1/task")
public class TaskController {

    private final InsertTaskInputPort insertTaskInputPort;
    private final FindAllTasksInputPort findAllTasksInputPort;

    public TaskController(InsertTaskInputPort insertTaskInputPort,
                          FindAllTasksInputPort findAllTasksInputPort) {
        this.insertTaskInputPort = insertTaskInputPort;
        this.findAllTasksInputPort = findAllTasksInputPort;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody TaskRequest taskRequest) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCollection userCollection = (UserCollection) authentication.getPrincipal();
        insertTaskInputPort.insert(taskRequest.getMessage(), userCollection.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> register() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCollection userCollection = (UserCollection) authentication.getPrincipal();
        return new ResponseEntity<>(findAllTasksInputPort.findAll(userCollection.getId()), HttpStatus.OK);
    }
}
