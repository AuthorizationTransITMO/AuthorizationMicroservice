package ru.ifmo.rain.issuer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.rain.issuer.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, Long>{
    Optional<Account> findByNumber(String number);

}
