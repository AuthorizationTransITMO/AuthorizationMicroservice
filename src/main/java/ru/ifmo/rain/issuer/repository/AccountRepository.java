package ru.ifmo.rain.issuer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.rain.issuer.domain.Account;

public interface AccountRepository extends JpaRepository <Account, Long>{
}
