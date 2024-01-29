package com.example.IPRWCbackend.controllers;

import com.example.IPRWCbackend.daos.AccountDAO;
import com.example.IPRWCbackend.daos.RoleDAO;
import com.example.IPRWCbackend.exceptions.EmailTakenException;
import com.example.IPRWCbackend.models.Account;
import com.example.IPRWCbackend.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/account")
@CrossOrigin(origins = "${frontend_url}")
public class AccountController {

    private final AccountDAO accountDAO;
    private final RoleDAO roleDAO;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(AccountDAO accountDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.accountDAO = accountDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public void addAccount(@RequestBody Account account) throws EmailTakenException {
        if (accountDAO.findAccountByEmail(account.getEmail()).isPresent()) {
            throw new EmailTakenException();
        }
        Role customerRole = this.roleDAO.findRoleByName("customer");
        account.setRole(customerRole);

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        accountDAO.registerNewAccount(account);
    }
}