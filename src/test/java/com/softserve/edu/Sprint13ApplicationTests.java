package com.softserve.edu;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Sprint;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Sprint13ApplicationTests {

    private final MarathonService marathonService;
    private final ProgressService progressService;
    private final SprintService sprintService;
    private final TaskService taskService;
    private final UserService userService;

    public Sprint13ApplicationTests(MarathonService marathonService, ProgressService progressService, SprintService sprintService, TaskService taskService, UserService userService) {
        this.marathonService = marathonService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Autowired
    private void fillDataService() {

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setPassword("pass");
            user.setRole(User.Role.TRAINEE);
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);
            user.setEmail("user" + i + "@dh.com");
            userService.createOrUpdateUser(user);
        }

        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setPassword("pass");
            user.setRole(User.Role.MENTOR);
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);
            user.setEmail("user" + i + "@dh.com");
            userService.createOrUpdateUser(user);
        }

		Marathon marathon = new Marathon();
        marathon.setTitle("Java-Marathon");
        marathonService.createOrUpdate(marathon);

        for (int i = 0; i < 5; i++) {
            Sprint sprint = new Sprint();
            sprint.setStart(LocalDate.now());
            sprint.setFinish(LocalDate.now().plusDays(3L));
            sprint.setTitle("Sprint" + i);
            sprint.setMarathon(marathon);
            sprintService.addSprintToMarathon(sprint, marathon);
        }

        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            task.setTitle("Task" + i);
            task.setCreated(LocalDate.now());
            task.setSprint(sprintService.getSprintById(2L));
        }
        
        
    }

}
