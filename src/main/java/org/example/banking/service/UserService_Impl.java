package org.example.banking.service;


import lombok.AllArgsConstructor;
import org.example.banking.dto.UserDTO;
import org.example.banking.entity.User;
import org.example.banking.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService_Impl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public UserDTO findByUserId(UUID uuid) {
        Optional<User> userOptional = userRepository.findById(uuid); //TODO: add gloabal exception
        return modelMapper.map(userOptional, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) { //TODO: return a response object
        User user = modelMapper.map(userDTO, User.class);
        user =  userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UUID userId, UserDTO userDTO) throws Exception {
        //TODO : Exception handling
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));;
        user.setUserName(userDTO.getUserName());
        user.setEmailAddress(userDTO.getEmailAddress());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(UUID userId) throws Exception {
        //TODO : Exception handling
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        userRepository.delete(user);
    }

}
