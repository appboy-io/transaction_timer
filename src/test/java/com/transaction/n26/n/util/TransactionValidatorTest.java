package com.transaction.n26.n.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

@RunWith(SpringRunner.class)
public class TransactionValidatorTest {

    private static final Long SEVENTY_SECOND_TRANSACTION_DIFF_MILLI = Long.valueOf(70000);


    @Test
    public void originalTimeGreaterThanCurrent() {
        long current = Instant.now().toEpochMilli();
        long original = current + 1L;
        Assert.assertTrue(TransactionValidator.transactionOnTime(current, original));
    }

    @Test
    public void originalTimeLessThanCurrentReturnTrue() {
        long current = Instant.now().toEpochMilli();
        long original = current - 1L;
        Assert.assertTrue(TransactionValidator.transactionOnTime(current, original));
    }

    @Test
    public void originalTimeLessThanCurrentReturnFalse() {
        long current = Instant.now().toEpochMilli();
        long original = current - SEVENTY_SECOND_TRANSACTION_DIFF_MILLI;
        Assert.assertFalse(TransactionValidator.transactionOnTime(current, original));
    }
}
