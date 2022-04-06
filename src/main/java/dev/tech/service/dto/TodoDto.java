package dev.tech.service.dto;

import dev.tech.domain.Status;

import java.util.Objects;

public class TodoDto {
    private Long id;

    private String task;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TodoDto() {
    }

    public TodoDto(Long id, String task, Status status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoDto)) return false;
        TodoDto todoDto = (TodoDto) o;
        return Objects.equals(id, todoDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", status=" + status +
                '}';
    }
}