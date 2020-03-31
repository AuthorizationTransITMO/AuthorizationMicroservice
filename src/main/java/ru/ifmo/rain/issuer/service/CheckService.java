package ru.ifmo.rain.issuer.service;

import org.springframework.stereotype.Service;
import ru.ifmo.rain.issuer.domain.Account;
import ru.ifmo.rain.issuer.domain.Log;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.repository.AccountRepository;
import ru.ifmo.rain.issuer.repository.LogRepository;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CheckService {
    private final AccountRepository accountRepository;
    private final LogRepository logRepository;

    private boolean compareTransaction(Transaction transaction) {
        return (hash(transaction.getNumber(), transaction.getDate()).equals(transaction.getCvv2()));
    }
    private boolean compareDB(Transaction transaction, Account account) {
        return (hash(transaction.getNumber(), transaction.getDate()).equals(hash(account.getNumber(), account.getOverDate().toString())));
    }


    public CheckService(AccountRepository accountRepository, LogRepository logRepository) {
        this.accountRepository = accountRepository;
        this.logRepository = logRepository;
    }

    private String hash(String number, String date){
        int hash = number.toString().hashCode() + date.toString().hashCode();

        String returningHash = "";
        hash %= 1000;

        if (hash < 100) {
            returningHash  = "0";
        }

        returningHash = returningHash.concat(String.valueOf(hash));
        return returningHash;
    }

    public Optional<Account> findByNumber(String number){
        return (number == null ? null : accountRepository.findByNumber(number));
    }

    public String check(Transaction transaction) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        if (transaction.getDate().equals(dtf.toString()))
            return "Card date is not valid";

        if (!compareTransaction(transaction))
            return "Hash is not valid";

        Account account = findByNumber(transaction.getNumber()).orElse(new Account());
        Account destinationAccount = findByNumber(transaction.getTargetPlace()).orElse(new Account());

        if (account == null)
            return "No such account";

        if (destinationAccount == null)
            return "No destination";

        if (!compareDB(transaction, account))
            return "Hash does not match DB";

        if (account.getOverDate().equals(transaction.getDate()))
            return "DB overDate does not match";

        if (transaction.getAction() == "Take"); {
            account.setCount(account.getCount() - transaction.getCount());
            destinationAccount.setCount(destinationAccount.getCount() + transaction.getCount());
        }
        if (transaction.getAction() == "Add") {
            account.setCount(account.getCount() + transaction.getCount());
            destinationAccount.setCount(destinationAccount.getCount() - transaction.getCount());
        }


        accountRepository.save(account);
        accountRepository.save(destinationAccount);



        Log log = new Log();
        log.setTransactionJson(transaction.toString());
        log.setId(7);
        logRepository.save(log);



        return "OK";
    }
}
