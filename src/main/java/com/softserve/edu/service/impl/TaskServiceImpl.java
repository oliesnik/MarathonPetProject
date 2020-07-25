package com.softserve.edu.service.impl;

import com.softserve.edu.model.Sprint;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.TaskRepository;
import com.softserve.edu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    final private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTaskToSprint(Task task, Sprint sprint) {
        if (task.getId() != null) {
            taskRepository.getOne(sprint.getId());
        }
        taskRepository.save(task);
        task.setSprint(sprint);
        return task;
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }
}
