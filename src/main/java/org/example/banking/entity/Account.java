package org.example.banking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.banking.dto.AccountType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/*
    The Account entity class represents a bank account in the system. It contains fields for the account ID, associated
    user, account number, balance, creation and update timestamps, and account type. The class is annotated with JPA
    annotations to specify the mapping between the class and the database table. The accountId field is marked as the
    primary key and is generated using UUID strategy. The user field is a one-to-one relationship with the User entity,
    and it is joined on the user_id column. The accountType field is of type AccountType, which is an enum representing
    the type of bank account (e.g., current or savings).
*/

@Entity
@Table(name="account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String accountNumber;
    private BigDecimal balance;
    private LocalDate createdOn;
    private LocalDate updatedOn;

    //@Enumerated(EnumType.STRING)
    private AccountType accountType;

}
