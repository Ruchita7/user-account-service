package org.example.banking.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// This is a Data Transfer Object (DTO) for the User entity. It is used to transfer data between different layers of the application, such as between the service layer and the controller layer. The DTO contains only the necessary fields that are required for the operations being performed, and it may not include all the fields present in the User entity. This helps to decouple the internal representation of the data from the external representation, allowing for more flexibility and maintainability in the application.
public class UserDTO {

    private UUID userId;

    private String userName;
    private String emailAddress;
}
