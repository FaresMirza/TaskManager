package org.example.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FakeTaskRepositoryTest {

    private FakeTaskRepository fakeTaskRepository;

    @BeforeEach
    public void setUp() {
        fakeTaskRepository = new FakeTaskRepository();
    }

    @Test
    public void testFindAll() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Task 1");
        task1.setDuration(2);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setName("Task 2");
        task2.setDuration(3);

        fakeTaskRepository.save(task1);
        fakeTaskRepository.save(task2);

        List<Task> tasks = fakeTaskRepository.findAll();
        assertEquals(2, tasks.size());
    }

    @Test
    public void testFindById() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDuration(2);
        fakeTaskRepository.save(task);

        Optional<Task> foundTask = fakeTaskRepository.findById(1L);
        assertTrue(foundTask.isPresent());
        assertEquals("Task 1", foundTask.get().getName());
    }

    @Test
    public void testSave() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDuration(2);
        fakeTaskRepository.save(task);

        List<Task> tasks = fakeTaskRepository.findAll();
        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
    }

    @Test
    public void testDeleteById() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDuration(2);
        fakeTaskRepository.save(task);

        fakeTaskRepository.deleteById(1L);
        List<Task> tasks = fakeTaskRepository.findAll();
        assertEquals(0, tasks.size());
    }
}