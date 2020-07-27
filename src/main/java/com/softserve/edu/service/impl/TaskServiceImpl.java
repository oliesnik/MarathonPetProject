package com.softserve.edu.service.impl;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Sprint;
import com.softserve.edu.model.Task;
import com.softserve.edu.repository.SprintRepository;
import com.softserve.edu.repository.TaskRepository;
import com.softserve.edu.service.TaskService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    final private TaskRepository taskRepository;
    final SprintRepository sprintRepository;

    public TaskServiceImpl(TaskRepository taskRepository, SprintRepository sprintRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    @Transactional
    public Task addTaskToSprint(Task task, Sprint sprint) {
        Optional<Sprint> sprintEntityOpt = sprintRepository.findById(sprint.getId());
        if (sprintEntityOpt.isPresent()){
            task.setSprint(sprintEntityOpt.get());
            return taskRepository.save(task);
        }
        throw new EntityNotFoundException(("No task /w id " + sprint.getId()));
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No Task exist for given id")));
    }
}
