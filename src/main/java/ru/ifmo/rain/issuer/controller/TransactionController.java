package ru.ifmo.rain.issuer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ifmo.rain.issuer.domain.Transaction;
import ru.ifmo.rain.issuer.service.CheckService;

import javax.validation.Valid;

@Controller
@RequestMapping("/issuerAPI/1/")
public class TransactionController {

    private final CheckService checkService;

    public TransactionController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/handle")
    public String handle(@Valid @ModelAttribute("transaction") Transaction transaction,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Incorrect transaction";
        }
        return checkService.check(transaction);
    }
}
