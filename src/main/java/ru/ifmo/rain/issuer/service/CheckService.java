package ru.ifmo.rain.issuer.service;

import org.springframework.stereotype.Service;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.repository.AccountRepository;
import ru.ifmo.rain.issuer.repository.HashContainerRepository;

@Service
public class CheckService {
    private final AccountRepository accountRepository;
    private final HashContainerRepository hashContainerRepository;

    public CheckService(AccountRepository accountRepository, HashContainerRepository hashContainerRepository) {
        this.accountRepository = accountRepository;
        this.hashContainerRepository = hashContainerRepository;
    }

    public String check(Transaction transaction) {
        // TODO
        return null;
    }
}
