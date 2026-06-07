package org.example.banking.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// This is a Data Transfer Object (DTO) for the Account entity. It is used to transfer data between different layers of the application, such as between the service layer and the controller layer. The DTO contains only the necessary fields that are required for the operations being performed, and it may not include all the fields present in the Account entity. This helps to decouple the internal representation of the data from the external representation, allowing for more flexibility and maintainability in the application.
public class AccountDTO {

    private UUID accountId;
    private UUID userId;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private AccountType accountType;
}
