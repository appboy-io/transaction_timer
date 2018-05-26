package com.transaction.n26.n.util;

public class TransactionValidator {
    private TransactionValidator(){}

    private static final Long MAX_TRANSACTION_DIFF = Long.valueOf(60);
    private static final Long MILLI_TO_SECOND_DIV = Long.valueOf(1000);

    public static boolean transactionOnTime(long current, long original) {
        if(original > current) {
            return true;
        }

        Long diff = Math.abs(current - original)/MILLI_TO_SECOND_DIV;
        return diff < MAX_TRANSACTION_DIFF;
    }
}
