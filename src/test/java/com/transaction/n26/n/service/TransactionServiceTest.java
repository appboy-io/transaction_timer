package com.transaction.n26.n.service;

import com.transaction.n26.n.model.Stats;
import com.transaction.n26.n.model.Transaction;
import com.transaction.n26.n.model.TransactionDto;
import com.transaction.n26.n.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    private static final Long MAX_TRANSACTION_DIFF_MILLI = Long.valueOf(60000);
    private static final Long TEN_SECOND_TRANSACTION_DIFF_MILLI = Long.valueOf(10000);
    private static final Long SEVENTY_SECOND_TRANSACTION_DIFF_MILLI = Long.valueOf(70000);

    @MockBean
    TransactionRepository mockTransactionRepository;
    TransactionService transactionService;


    @Before
    public void setup() {
        transactionService = new TransactionServiceImpl(mockTransactionRepository);
    }

    @Test
    public void testResponseFromTransactionsTrue() {
        long transactionTimeStamp = Instant.now().toEpochMilli();
        long dtoTimeStamp = transactionTimeStamp - TEN_SECOND_TRANSACTION_DIFF_MILLI;
        TransactionDto testDto = new TransactionDto(Double.valueOf("1"), dtoTimeStamp);

        Assert.assertTrue(transactionService.addTransaction(testDto, transactionTimeStamp));
    }

    @Test
    public void testResponseFromTransactionsFalse() {
        long transactionTimeStamp = Instant.now().toEpochMilli();
        long dtoTimeStamp = transactionTimeStamp - SEVENTY_SECOND_TRANSACTION_DIFF_MILLI;
        TransactionDto testDto = new TransactionDto(Double.valueOf("1"), dtoTimeStamp);
        Assert.assertFalse(transactionService.addTransaction(testDto, transactionTimeStamp));
    }

    @Test
    public void testStatsRetreival() {
        long transactionTimeStamp = Instant.now().toEpochMilli();
        List<Transaction> mockTransactions = new ArrayList<Transaction>();
        mockTransactions.add(new Transaction(Double.valueOf("1.0"), transactionTimeStamp));
        mockTransactions.add(new Transaction(Double.valueOf("1.0"), transactionTimeStamp));
        mockTransactions.add(new Transaction(Double.valueOf("1.0"), transactionTimeStamp));
        Mockito.when(mockTransactionRepository.findByTimestampBetweenQuery(Mockito.anyLong(), Mockito.anyLong())).thenReturn(mockTransactions);
        Stats result = transactionService.getStats(transactionTimeStamp);
        Assert.assertEquals(Long.valueOf(3), result.getCount());
        Assert.assertEquals(Double.valueOf(3.0), result.getSum());
    }
}
