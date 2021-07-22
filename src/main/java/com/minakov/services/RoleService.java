package com.minakov.services;

import com.minakov.entities.Role;
import com.minakov.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Service class to control Roles in DB
 */
@Controller
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findAll()
                .stream()
                .filter(role -> role.getName().equals(roleName))
                .findFirst()
                .orElse(null);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }
}
