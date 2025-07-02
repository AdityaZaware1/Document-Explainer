package com.ben.Document_Explainer.repo;

import com.ben.Document_Explainer.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUsersByEmail(@Email String email);

    User findUsersById(Long id);
}
