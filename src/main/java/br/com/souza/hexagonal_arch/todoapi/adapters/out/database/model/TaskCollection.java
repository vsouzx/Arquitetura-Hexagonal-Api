package br.com.souza.hexagonal_arch.todoapi.adapters.out.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "col_task")
public class TaskCollection {

    @Id
    private String id;
    private String message;
    private Boolean isDone;
    private String userId;

}
