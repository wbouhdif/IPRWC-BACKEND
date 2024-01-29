package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
public interface AccountRepository extends JpaRepository<Account, UUID> {


    @Query("SELECT a FROM Account a WHERE a.email = ?1")
    Optional<Account> findAccountByEmail(String email);


}
