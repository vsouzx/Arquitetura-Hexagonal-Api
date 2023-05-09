package br.com.souza.hexagonal_arch.todoapi.application.ports.in.task;

public interface InsertTaskInputPort {

    void insert(String message, String userId) throws Exception;
}
