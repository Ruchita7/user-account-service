package org.example.banking;

import org.example.banking.dto.AccountDTO;
import org.example.banking.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    This is a configuration class for the application. It defines a bean for ModelMapper, which is a library used for
    mapping between different object models, such as between entities and DTOs. The ModelMapper bean is configured to
    skip the user field when mapping from AccountDTO to Account, and to map the userId from Account to AccountDTO when
    mapping in the opposite direction. This allows for more control over how the mapping is performed, especially when
     dealing with complex relationships between entities and DTOs.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.createTypeMap(AccountDTO.class, Account.class)
                .addMappings(m -> m.skip(Account::setUser));  // ← skip user, set it manually

        mapper.createTypeMap(Account.class, AccountDTO.class)
                .addMappings(m -> m.map(src -> src.getUser().getUserId(), AccountDTO::setUserId));
        return mapper;
    }
}
