package ru.ifmo.rain.issuer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    private static class Response {
        public String verdict;
    }

    @GetMapping("/handle")
    @ResponseBody
    public Response handle(@RequestBody @Valid Transaction transaction,
                           BindingResult bindingResult) {
        Response response = new Response();
        if (bindingResult.hasErrors()) {
            response.verdict = "Incorrect transaction";
        } else {
            response.verdict = checkService.check(transaction);
        }
        return response;
    }
}
