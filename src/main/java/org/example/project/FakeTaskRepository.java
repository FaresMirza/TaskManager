package org.example.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeTaskRepository implements TaskRepositoryInterface {

    private List<Task> tasks = new ArrayList<>();


    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }


    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }


    public Task save(Task task) {
        tasks.removeIf(t -> t.getId().equals(task.getId())); // Remove if exists
        tasks.add(task);
        return task;
    }


    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}