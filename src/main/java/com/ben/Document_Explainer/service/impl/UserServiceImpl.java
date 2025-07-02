package com.ben.Document_Explainer.service.impl;

import com.ben.Document_Explainer.entity.User;
import com.ben.Document_Explainer.repo.UserRepo;
import com.ben.Document_Explainer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User addUser(User user) {
        if (userRepo.getUsersByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user, Long userid) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepo.findUsersById(id);

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepo.getUsersByEmail(email);

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }
}
