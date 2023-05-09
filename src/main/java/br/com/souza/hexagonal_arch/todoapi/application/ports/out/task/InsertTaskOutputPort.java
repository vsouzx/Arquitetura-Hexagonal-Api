package br.com.souza.hexagonal_arch.todoapi.application.ports.out.task;

public interface InsertTaskOutputPort {

    void save(String message, String userId);
}
