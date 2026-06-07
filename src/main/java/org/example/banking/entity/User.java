package org.example.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * The User entity class represents a user in the banking system. It contains fields for userId, userName, and
 * emailAddress. The class is annotated with JPA annotations to specify the mapping between the class and the database
 * table. The userId field is marked as the primary key and is generated using UUID strategy.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String userName;
    private String emailAddress;
}
