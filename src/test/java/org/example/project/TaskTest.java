package org.example.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskGettersAndSetters() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Test Task");
        task.setDuration(5);

        assertEquals(1L, task.getId(), "The task ID should be 1");
        assertEquals("Test Task", task.getName(), "The task name should be 'Test Task'");
        assertEquals(5, task.getDuration(), "The task duration should be 5");
    }

    @Test
    public void testTaskConstructor() {
        Task task = new Task();
        task.setId(2L);
        task.setName("Sample Task");
        task.setDuration(10);

        assertNotNull(task, "The task object should not be null");
        assertEquals(2L, task.getId(), "The task ID should be 2");
        assertEquals("Sample Task", task.getName(), "The task name should be 'Sample Task'");
        assertEquals(10, task.getDuration(), "The task duration should be 10");
    }

    @Test
    public void testTaskEquality() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Task 1");
        task1.setDuration(5);

        Task task2 = new Task();
        task2.setId(1L);
        task2.setName("Task 1");
        task2.setDuration(5);

        Task task3 = new Task();
        task3.setId(2L);
        task3.setName("Task 2");
        task3.setDuration(10);

        assertEquals(task1, task2, "Tasks with the same ID, name, and duration should be equal");
        assertNotEquals(task1, task3, "Tasks with different ID, name, or duration should not be equal");
    }

    @Test
    public void testTaskHashCode() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Task 1");
        task1.setDuration(5);

        Task task2 = new Task();
        task2.setId(1L);
        task2.setName("Task 1");
        task2.setDuration(5);

        Task task3 = new Task();
        task3.setId(2L);
        task3.setName("Task 2");
        task3.setDuration(10);

        assertEquals(task1.hashCode(), task2.hashCode(), "Hash codes should be equal for tasks with the same fields");
        assertNotEquals(task1.hashCode(), task3.hashCode(), "Hash codes should not be equal for tasks with different fields");
    }

    @Test
    public void testTaskToString() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDuration(5);

        String taskString = task.toString();

        assertTrue(taskString.contains("1"), "The string representation should contain the task ID");
        assertTrue(taskString.contains("Task 1"), "The string representation should contain the task name");
        assertTrue(taskString.contains("5"), "The string representation should contain the task duration");
    }

    @Test
    public void testTaskDefaultValues() {
        Task task = new Task();

        assertNull(task.getId(), "The default ID should be null");
        assertNull(task.getName(), "The default name should be null");
        assertEquals(0, task.getDuration(), "The default duration should be 0");
    }
}