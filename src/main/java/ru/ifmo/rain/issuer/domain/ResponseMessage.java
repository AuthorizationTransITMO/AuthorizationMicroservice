package ru.ifmo.rain.issuer.domain;


import java.util.Date;

public class ResponseMessage {
    public Object verdict;
    public long id = -1;
    public String date;

    public ResponseMessage() {};

    public ResponseMessage(String verdict) {
            this.verdict = verdict;
        }

    public ResponseMessage(String verdict, long id, String date) {
            this.verdict = verdict;
            this.id = id;
            this.date = date;
    }
}