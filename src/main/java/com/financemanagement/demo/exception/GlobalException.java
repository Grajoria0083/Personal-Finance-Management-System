package com.financemanagement.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<MyErrorDetail> custumerException(CustomerException ee, WebRequest wr){

        MyErrorDetail error = new MyErrorDetail();
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessege(ee.getMessage());
        error.setError(wr.getDescription(false));

        return new  ResponseEntity<MyErrorDetail>(error, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<MyErrorDetail> transactionException(TransactionException ee, WebRequest wr){

        MyErrorDetail error = new MyErrorDetail();
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessege(ee.getMessage());
        error.setError(wr.getDescription(false));

        return new  ResponseEntity<MyErrorDetail>(error, HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetail> Exception(Exception ee, WebRequest wr){

        MyErrorDetail error = new MyErrorDetail();
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessege(ee.getMessage());
        error.setError(wr.getDescription(false));

        return new ResponseEntity<MyErrorDetail>(error, HttpStatus.BAD_REQUEST);

    }
}
