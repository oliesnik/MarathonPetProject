package com.softserve.edu;

import com.softserve.edu.model.*;
import com.softserve.edu.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

@SpringBootApplication
@Slf4j
public class Sprint13Application implements CommandLineRunner {
    private final MarathonService marathonService;
    private final ProgressService progressService;
    private final SprintService sprintService;
    private final TaskService taskService;
    private final UserService userService;

    public Sprint13Application(MarathonService marathonService, ProgressService progressService,
                               SprintService sprintService, TaskService taskService, UserService userService) {
        this.marathonService = marathonService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Sprint13Application.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {
        log.info("Running Spring Boot Application");

        try {
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setPassword("pass");
                user.setRole(User.Role.TRAINEE);
                user.setFirstName("firstNameq" + i);
                user.setLastName("lastName" + i);
                user.setEmail("user" + i + "@dh.com");
                userService.createOrUpdateUser(user);
            }

//        User user = userService.getUserById();
//        user.setPassword("newPassword");

            Progress progress = new Progress();
            progress.setStarted(LocalDate.now());
            progress.setStatus(Progress.TaskStatus.PASS);
            progressService.addOrUpdateProgress(progress);

            for (int i = 0; i < 5; i++) {
                Task task = new Task();
                task.setTitle("Task" + i);
                task.setCreated(LocalDate.now());
                taskService.addTaskToSprint(task, new Sprint());
            }
            Marathon marathon = new Marathon();
            marathon.setTitle("Java-Marathon");
            marathonService.createOrUpdate(marathon);

            for (int i = 0; i < 5; i++) {
                Sprint sprint = new Sprint();
                sprint.setStart(LocalDate.now());
                sprint.setFinish(LocalDate.now().plusDays(3L));
                sprint.setTitle("Sprint" + i);
                sprintService.updateSprint(sprint);
            }
        } catch (ConstraintViolationException e) {
            e.getMessage();
        }
    }

}
