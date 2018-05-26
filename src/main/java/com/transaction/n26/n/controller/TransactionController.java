package com.transaction.n26.n.controller;

import com.transaction.n26.n.model.Stats;
import com.transaction.n26.n.model.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface TransactionController {
    ResponseEntity addTransaction(TransactionDto transactionDto);
    ResponseEntity<Stats> getStats();
}
