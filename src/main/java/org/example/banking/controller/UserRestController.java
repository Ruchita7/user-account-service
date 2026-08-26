package org.example.banking.controller;

import org.example.banking.dto.UserDTO;
import org.example.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * UserRestController is a REST controller that handles HTTP requests related to user operations.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing application users")

public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing the list of UserDTOs and HTTP status OK.
     */
    @GetMapping
    @Operation(
            summary = "Find all users",
            description = "Provides full user details"
    )
    @ApiResponse(responseCode = "200", description = "Users successfully found")
    @ApiResponse(responseCode = "404", description = "Users not found")
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
    @Operation(
            summary = "Get user by ID",
            description = "Provides full user details based on the unique user database ID."
    )
    @ApiResponse(responseCode = "200", description = "User successfully found")
    @ApiResponse(responseCode = "404", description = "User not found")
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
    @Operation(
            summary = "Create user",
            description = "Create a new user"
    )
    @ApiResponse(responseCode = "204", description = "User successfully created")
    @ApiResponse(responseCode = "404", description = "Bad request")
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
    @Operation(
            summary = "Update user",
            description = "Update an existing user"
    )
    @ApiResponse(responseCode = "204", description = "User successfully updated")
    @ApiResponse(responseCode = "404", description = "Bad request")
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
    @Operation(
            summary = "Delete user",
            description = "Delete an existing user"
    )
    @ApiResponse(responseCode = "204", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "Bad request")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
