package ru.ifmo.rain.issuer.service;

import org.springframework.stereotype.Service;
import ru.ifmo.rain.issuer.domain.Account;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.repository.AccountRepository;

import java.util.Optional;

@Service
public class CheckService {
    private final AccountRepository accountRepository;

    public CheckService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String check(Transaction transaction) {
        // пример запроса аккаунта
        // на нулы провере
        Optional<Account> account = accountRepository.findByNumber(transaction.getNumber());
        // TODO
        // пример изменения аккаунта
        account.get().setCount(0d);
        accountRepository.save(account.get());
        return "OK";
    }
}
