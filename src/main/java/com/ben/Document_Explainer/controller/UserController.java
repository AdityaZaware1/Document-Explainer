package com.ben.Document_Explainer.controller;

import com.ben.Document_Explainer.entity.Document;
import com.ben.Document_Explainer.entity.User;
import com.ben.Document_Explainer.service.DocumentService;
import com.ben.Document_Explainer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final DocumentService documentService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getDocument/{userId}")
    public ResponseEntity<Document> getDocumentByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(documentService.getDocument(userId));
    }

    @GetMapping("/getUser/{userId})")
    public ResponseEntity<Document> getDocument(@PathVariable Long userId) {
        return ResponseEntity.ok(documentService.getDocument(userId));
    }
}
