package org.example.banking.controller;

import org.example.banking.dto.UserDTO;
import org.example.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * UserRestController is a REST controller that handles HTTP requests related to user operations.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing the list of UserDTOs and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userList = userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the UUID of the user to retrieve.
     * @return ResponseEntity containing the UserDTO and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id) {
        UserDTO user = userService.findByUserId(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Creates a new user.
     *
     * @param userDTO the UserDTO containing the details of the user to create.
     * @return ResponseEntity containing the created UserDTO and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO userResponse = userService.createUser(userDTO);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    /* *
     * Updates an existing user.
     *
     * @param id the UUID of the user to update.
     * @param userDTO the UserDTO containing the updated details of the user.
     * @return ResponseEntity containing the updated UserDTO and HTTP status OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        UserDTO userResponse = null;
        try {
            userResponse = userService.updateUser(id, userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /* *
     * Deletes a user by their unique identifier.
     *
     * @param id the UUID of the user to delete.
     * @return ResponseEntity with HTTP status NO_CONTENT if deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
