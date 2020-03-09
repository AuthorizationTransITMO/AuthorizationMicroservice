package ru.ifmo.rain.issuer.service;

import org.springframework.stereotype.Service;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.repository.AccountRepository;

@Service
public class CheckService {
    private final AccountRepository accountRepository;

    public CheckService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String check(Transaction transaction) {
        // TODO
        return null;
    }
}
