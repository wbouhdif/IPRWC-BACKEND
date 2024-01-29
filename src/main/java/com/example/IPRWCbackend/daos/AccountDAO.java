package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Optional;


@Component
@CrossOrigin(origins = "${frontend_url}")
public class AccountDAO {

    private final AccountRepository accountRepository;

    public AccountDAO(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void registerNewAccount(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

}
