package dev.tech.domain;

public enum Status {
    TO_DO ("TO DO"),
    IN_PROGRESS ("IN PROGRESS"),
    DONE ("DONE");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}