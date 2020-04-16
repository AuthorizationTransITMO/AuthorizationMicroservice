package ru.ifmo.rain.issuer.service;

import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import ru.ifmo.rain.issuer.domain.Account;
import ru.ifmo.rain.issuer.domain.Log;
import ru.ifmo.rain.issuer.domain.ResponseMessage;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.repository.AccountRepository;
import ru.ifmo.rain.issuer.repository.LogRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class CheckService {
    private final AccountRepository accountRepository;
    private final LogRepository logRepository;

    private boolean compareTransaction(Transaction transaction) {
        return (hash(transaction.getNumber(), transaction.getDate()).equals(transaction.getCvv2()));
    }
    private boolean compareDB(Transaction transaction, Account account) {
        return (hash(transaction.getNumber(), transaction.getDate()).equals(hash(account.getNumber(), customlyParseDate(account.getOverDate()))));
    }

    public String customlyParseDate(Date date) {
        String newDate = "";
        newDate = newDate.concat(date.toString().split("-")[1]);
        //System.out.println(date.toString().split("-")[1]);
        newDate = newDate.concat("/");
        newDate = newDate.concat(date.toString().split("-")[0]);
        return newDate;
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

    public Object check(Transaction transaction) {

        if (!compareTransaction(transaction))
            return "Hash is not valid";

        Account account = findByNumber(transaction.getNumber()).orElse(new Account());

        LocalDate ld = LocalDate.now();
        String curDate = "";
        curDate = curDate.concat(String.valueOf(ld.getMonthValue()));
        curDate = curDate.concat("/");
        curDate = curDate.concat((String.valueOf(ld.getYear())));

        //System.out.println(curDate);
        if (transaction.getDate().compareTo(curDate) > 0) {
            return "Card is outdated";
        }


        if (account == null)
            return "No such account";

        if (!compareDB(transaction, account))
            return "Hash does not match DB";

        if (!transaction.getDate().equals(customlyParseDate(account.getOverDate())))
            return "DB overDate does not match";


        //System.out.println(account.getCount());
        //System.out.println(transaction.getCount());
        if (transaction.getAction() == "Take"); {
            if (account.getCount() < transaction.getCount())
                return "Insufficient funds";
            account.addCount((account.getCount() - transaction.getCount()));
        }
        if (transaction.getAction() == "Add") {
            account.substractCount(account.getCount() + transaction.getCount());
        }

        if (transaction.getAction() == null) {
            return "Please specify the action";
        }

        if ((transaction.getAction().compareTo("Take") != 0) && (transaction.getAction().compareTo("Add") != 0)) {
            System.out.println(transaction.getAction());
            return "This action is not allowed";
        }

        Log log = new Log();
        log.setTransactionJson(transaction.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String cd = transaction.getDateAction();
        LocalDate led = LocalDate.parse(cd, formatter);
        log.setDate(led);

        log.setAccount(account);
        log.setReturnedMessage("OK");
        System.out.println(log.getDate());
        logRepository.save(log);

        return new ResponseMessage("OK", log.getId(), transaction.getDateAction());
    }
}
