package br.com.souza.hexagonal_arch.todoapi.application.core.domains;

public class Task {

    private String id;
    private String message;
    private Boolean isDone;
    private String userId;

    public Task() {
        this.isDone = false;
    }

    public Task(String id, String message, Boolean isDone, String userId) {
        this.id = id;
        this.message = message;
        this.isDone = isDone;
        this.userId = userId;
    }

    public Task(String message, String userId) {
        this.message = message;
        this.isDone = false;
        this.userId = userId;
    }

    public Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
