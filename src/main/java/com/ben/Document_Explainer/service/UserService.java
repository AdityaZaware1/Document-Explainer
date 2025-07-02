package com.ben.Document_Explainer.service;

import com.ben.Document_Explainer.entity.User;

public interface UserService {

    User addUser(User user);
    User updateUser(User user, Long userid);
    User getUserById(Long id);
    void deleteUser(Long id);
    User getUserByEmail(String email);
}
