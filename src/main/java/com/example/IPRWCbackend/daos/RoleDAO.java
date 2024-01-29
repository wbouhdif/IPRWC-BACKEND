package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDAO(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElse(null);
    }
}
