package com.transaction.n26.n.controller;

import com.transaction.n26.n.model.Stats;
import com.transaction.n26.n.model.TransactionDto;
import com.transaction.n26.n.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    @Autowired
    TransactionControllerImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    @PostMapping("/transactions")
    public ResponseEntity addTransaction(@RequestBody TransactionDto transactionDto) {
        boolean transactionAdded = transactionService.addTransaction(transactionDto, Instant.now().toEpochMilli());

        return transactionAdded ? new ResponseEntity(HttpStatus.CREATED): new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/statistics")
    public ResponseEntity getStats() {
        return new ResponseEntity<Stats>(transactionService.getStats(Instant.now().toEpochMilli()),HttpStatus.OK);
    }
}
