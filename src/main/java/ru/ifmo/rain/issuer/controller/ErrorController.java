package ru.ifmo.rain.issuer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ifmo.rain.issuer.domain.ResponseMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public ResponseMessage renderErrorPage(HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.verdict = "transaction data is not valid";
        return responseMessage;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
