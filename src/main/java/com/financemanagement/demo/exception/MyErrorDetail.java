package com.financemanagement.demo.exception;

import java.time.LocalDateTime;

public class MyErrorDetail {

    String messege;
    LocalDateTime localDateTime;
    String error;


    public MyErrorDetail(String messege, LocalDateTime localDateTime, String error) {
        this.messege = messege;
        this.localDateTime = localDateTime;
        this.error = error;
    }

    public MyErrorDetail() {

    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
