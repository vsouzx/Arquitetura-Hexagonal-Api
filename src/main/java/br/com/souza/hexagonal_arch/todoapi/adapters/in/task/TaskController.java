package br.com.souza.hexagonal_arch.todoapi.adapters.in.task;

import br.com.souza.hexagonal_arch.todoapi.adapters.in.task.dto.TaskRequest;
import br.com.souza.hexagonal_arch.todoapi.application.core.domains.Task;
import br.com.souza.hexagonal_arch.todoapi.application.ports.in.task.InsertTaskInputPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todo/v1/task")
public class TaskController {

    private final InsertTaskInputPort insertTaskInputPort;

    public TaskController(InsertTaskInputPort insertTaskInputPort) {
        this.insertTaskInputPort = insertTaskInputPort;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody TaskRequest taskRequest) throws Exception{
        Task task = new Task(taskRequest.getMessage(), taskRequest.getUserId());
        insertTaskInputPort.insert(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
