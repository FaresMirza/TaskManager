package org.example.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // Mocking the TaskRepository

    @InjectMocks
    private TaskService taskService; // The service under test, with mocks injected

    private AutoCloseable closeable; // For closing mocks properly

    @BeforeEach
    public void setUp() {
        // Initialize the mocks before each test
        closeable = MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testGetAllTasks() {
        // Arrange
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Task 1");
        task1.setDuration(2);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setName("Task 2");
        task2.setDuration(3);

        List<Task> tasks = Arrays.asList(task1, task2);
        // Mock the behavior of taskRepository to return the list of tasks
        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<Task> result = taskService.getAllTasks();

        // Assert
        assertEquals(2, result.size(), "The size of the task list should be 2");
        assertEquals("Task 1", result.get(0).getName(), "The first task name should be 'Task 1'");
        verify(taskRepository, times(1)).findAll(); // Verify that findAll was called once
    }

    @Test
    public void testGetTaskById() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDuration(2);

        // Mock the behavior of taskRepository to return the task when findById is called
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Act
        Optional<Task> result = taskService.getTaskById(1L);

        // Assert
        assertTrue(result.isPresent(), "Task should be present");
        assertEquals("Task 1", result.get().getName(), "The task name should be 'Task 1'");
        verify(taskRepository, times(1)).findById(1L); // Verify that findById was called once
    }

    @Test
    public void testSaveTask() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDuration(2);

        // Mock the behavior of taskRepository to return the task when save is called
        when(taskRepository.save(task)).thenReturn(task);

        // Act
        Task result = taskService.saveTask(task);

        // Assert
        assertNotNull(result, "The saved task should not be null");
        assertEquals("Task 1", result.getName(), "The task name should be 'Task 1'");
        verify(taskRepository, times(1)).save(task); // Verify that save was called once
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        Long taskId = 1L;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).deleteById(taskId); // Verify that deleteById was called once
    }
}