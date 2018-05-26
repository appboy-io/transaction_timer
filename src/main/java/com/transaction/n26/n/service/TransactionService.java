package com.transaction.n26.n.service;

import com.transaction.n26.n.model.Stats;
import com.transaction.n26.n.model.TransactionDto;

public interface TransactionService {
    boolean addTransaction(TransactionDto dto, long transactionTime);
    Stats getStats(long transactionTime);
}
