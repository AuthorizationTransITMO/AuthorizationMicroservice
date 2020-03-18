package ru.ifmo.rain.issuer.service;

import org.springframework.stereotype.Service;
import ru.ifmo.rain.issuer.domain.Account;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.repository.AccountRepository;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CheckService {
    private final AccountRepository accountRepository;

    private boolean compareTransaction(Transaction transaction) {
        return (hash(transaction.getNumber(), transaction.getDate()).equals(transaction.getCvv2()));
    }
    private boolean compareDB(Transaction transaction, Account account) {
        return (hash(transaction.getNumber(), transaction.getDate()).equals(hash(account.getNumber(), account.getOverDate().toString())));
    }


    public CheckService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private String hash(String number, String date){
        int n = 0;
        for (int i = 0; i < date.length(); i++) {
            n += ((int) date.charAt(i)) * ((int)number.charAt(0));
        }

        n = (n % 900) + 100;
        String s = "333";
        System.out.println(s);
        return String.valueOf(s);

    }

    public Optional<Account> findByNumber(String number){
        return (number == null ? null : accountRepository.findByNumber(number));
    }

    public String check(Transaction transaction) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        if (transaction.getDate().equals(dtf.toString()))
            return "Card is outdated";

        if (!compareTransaction(transaction))
            return "Hash is not valid";

        Account account = findByNumber(transaction.getNumber()).orElse(new Account());

        if (account == null)
            return "No such account";

        if (!compareDB(transaction, account))
            return "Hash does not match DB";

        if (account.getOverDate().equals(transaction.getDate()))
            return "DB overDate does not match";

        accountRepository.save(account);
        return "OK";
    }
}
