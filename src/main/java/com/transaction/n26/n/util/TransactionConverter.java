package com.transaction.n26.n.util;

import com.transaction.n26.n.model.Transaction;
import com.transaction.n26.n.model.TransactionDto;

public class TransactionConverter {
    private TransactionConverter(){}

    public static Transaction transactionDtoToTransaction(TransactionDto transactionDto) {
        return new Transaction(transactionDto.getAmount(), transactionDto.getTimestamp());
    }
}
