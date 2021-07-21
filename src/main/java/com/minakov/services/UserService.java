package com.minakov.services;

import com.minakov.dto.UserDto;
import com.minakov.entities.RoleEnum;
import com.minakov.entities.User;
import com.minakov.exceptions.ApplicationException;
import com.minakov.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public boolean existsByLogin(String login) {
        return userRepository.findAll()
                .stream()
                .anyMatch(user -> login.equals(user.getLogin()));
    }

    public User getUserByLogin(String login) {
        return userRepository.findAll()
                .stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .orElse(null);
    }

    public User registerNewUser(UserDto userDto) throws ApplicationException {
        if (existsByLogin(userDto.getLogin())) {
            throw new ApplicationException("User with login '" + userDto.getLogin() + "' already exists");
        }
        User user = new User(userDto.getName(), userDto.getLogin(), userDto.getPhone(), userDto.getPassword(),
                roleService.getRoleByName(RoleEnum.CUSTOMER.name()));
        userRepository.save(user);
        return user;
    }
}
