package com.softserve.edu.service.impl;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private MarathonRepository marathonRepository;

    public UserServiceImpl(UserRepository userRepository, MarathonRepository marathonRepository) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()){
            return users;
        }
        return new ArrayList<>();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User createOrUpdateUser(User entity) {
      //  if (entity.getId() != null)

        return null;
    }

    @Override
    public List<User> getAllByRole(String role) {
        return null;
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        return false;
    }

    @Override
    public boolean addUserToTask(User user, Task task) {
        return false;
    }
}
