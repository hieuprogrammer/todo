package dev.tech.repository;

import dev.tech.domain.Status;
import dev.tech.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByTask(String task);

    List<Todo> findByStatus(Status status);
}