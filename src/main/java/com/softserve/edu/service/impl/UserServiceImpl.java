package com.softserve.edu.service.impl;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (!users.isEmpty()) {
            return users;
        }
        return new ArrayList<>();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new EntityNotFoundException("No user with such id!");
        }
    }

    @Override
    public User createOrUpdateUser(User entity) {
        if (entity.getId() != null) {

            Optional<User> user = userRepository.findById(entity.getId());
            if (user.isPresent()) {
                User newUser = user.get();
                newUser.setEmail(entity.getEmail());
                newUser.setFirstName(entity.getFirstName());
                newUser.setLastName(entity.getLastName());
                newUser.setRole(entity.getRole());
                newUser.setPassword(entity.getPassword());
                newUser = userRepository.save(newUser);
                return newUser;
            }
        }
        entity = userRepository.save(entity);
        return entity;
    }

    @Override
    public List<User> getAllByRole(String role) {
        return userRepository.getAllByRole(User.Role.valueOf(role.toUpperCase()));
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        User userEntity = userRepository.getOne(user.getId());

        return false;
    }

}
