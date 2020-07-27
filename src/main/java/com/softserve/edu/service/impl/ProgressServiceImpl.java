package com.softserve.edu.service.impl;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Progress;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.ProgressRepository;
import com.softserve.edu.repository.SprintRepository;
import com.softserve.edu.repository.TaskRepository;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.ProgressService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    final ProgressRepository progressRepository;
    final UserRepository userRepository;
    final SprintRepository sprintRepository;
    final TaskRepository taskRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository, UserRepository userRepository, SprintRepository sprintRepository, TaskRepository taskRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.sprintRepository = sprintRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No Progress exist for given id")));
    }

    @Override
    public Progress addTaskForStudent(Task task, User user) {
        Progress progress  = new Progress();
        progress.setTrainee(userRepository.getOne(user.getId()));
        progress.setTask(taskRepository.getOne(task.getId()));
        return progressRepository.save(progress);
    }

    @Override
    public Progress addOrUpdateProgress(Progress p) {
        if (p.getId() != null) {
            Optional<Progress> progress = progressRepository.findById(p.getId());
            if (progress.isPresent()) {
                Progress newProgress = progress.get();
                newProgress.setStarted(p.getStarted());
                newProgress.setUpdated(p.getUpdated());
                newProgress.setStatus(p.getStatus());
                newProgress = progressRepository.save(newProgress);
                return newProgress;
            }
        }
        p = progressRepository.save(p);
        return p;
    }

    @Override
    public boolean setStatus(Progress.TaskStatus status, Progress p) {
        Optional<Progress> progressEntityOpt = progressRepository.findById(p.getId());
        if (progressEntityOpt.isPresent()) {
            Progress progressEntity = progressEntityOpt.get();
            if (progressEntity.getStatus() != status) {
                progressEntity.setStatus(status);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
        return progressRepository.allProgressByUserIdAndMarathonId(userId, marathonId);
    }

    @Override
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        return progressRepository.allProgressByUserIdAndSprintId(userId, sprintId);
    }
}
