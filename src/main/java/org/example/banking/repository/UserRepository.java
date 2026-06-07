package org.example.banking.repository;

import org.example.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// This is a repository interface for the User entity. It extends JpaRepository, which provides basic CRUD operations for the User entity. By extending JpaRepository, this interface inherits methods for saving, deleting, and finding User entities in the database. The UUID type is used as the primary key for the User entity.
public interface UserRepository extends JpaRepository<User, UUID> {


}
