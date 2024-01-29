package com.example.IPRWCbackend.controllers;

import com.example.IPRWCbackend.daos.RoleDAO;
import com.example.IPRWCbackend.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/role")
@CrossOrigin(origins = "${frontend_url}")
public class RoleController {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleController(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @GetMapping("{name}")
    public ResponseEntity<Role> getRole(@PathVariable String name) {
        Role role = roleDAO.findRoleByName(name);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }
}
