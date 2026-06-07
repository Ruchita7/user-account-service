package org.example.banking.service;

import org.example.banking.dto.UserDTO;

import java.util.List;
import java.util.UUID;

/**
 * UserService is an interface that defines the operations related to user management in the banking application.
 * It provides methods for creating, retrieving, updating, and deleting user information.
 */
public interface UserService {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param uuid the UUID of the user to retrieve.
     * @return the UserDTO representing the user with the specified UUID.
     */
    UserDTO findByUserId(UUID uuid);

    /**
     * Creates a new user based on the provided UserDTO.
     *
     * @param userDTO the UserDTO containing the details of the user to create.
     * @return the UserDTO representing the newly created user.
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Retrieves a list of all users.
     *
     * @return a List of UserDTOs representing all users in the system.
     */
    List<UserDTO> findAllUsers();

    /**
     * Updates an existing user identified by the provided userId with the details from the provided UserDTO.
     *
     * @param userId  the UUID of the user to update.
     * @param userDTO the UserDTO containing the updated details of the user.
     * @return the UserDTO representing the updated user.
     * @throws Exception if the user with the specified UUID does not exist or if there is an error during the update process.
     */
    UserDTO updateUser(UUID userId, UserDTO userDTO) throws Exception;

    /*  *
     * Deletes a user identified by the provided userId.
     *
     * @param userId the UUID of the user to delete.
     * @throws Exception if the user with the specified UUID does not exist or if there is an error during the deletion process.
     */
    void deleteUser(UUID userId) throws Exception;
}
