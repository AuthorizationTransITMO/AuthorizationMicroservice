package ru.ifmo.rain.issuer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.rain.issuer.domain.ResponseMessage;
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
    @ResponseBody
    public ResponseMessage handle(@RequestBody @Valid Transaction transaction,
                                  BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            responseMessage.verdict = "Incorrect transaction";
        } else {
            responseMessage.verdict = checkService.check(transaction);
        }
        return responseMessage;
    }
}
