package com.transaction.n26.n.controller;

import com.transaction.n26.n.model.Stats;
import com.transaction.n26.n.model.TransactionDto;
import com.transaction.n26.n.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionControllerTest {
    @MockBean
    TransactionService transactionService;
    TransactionController transactionController;

    @Before
    public void setup() {
        transactionController = new TransactionControllerImpl(transactionService);
    }

    @Test
    public void addTransaction201Created() {
        when(transactionService.addTransaction(Mockito.anyObject(), Mockito.anyLong())).thenReturn(true);
        ResponseEntity result = transactionController.addTransaction(new TransactionDto());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void addTransaction204NoContent() {
        when(transactionService.addTransaction(Mockito.anyObject(), Mockito.anyLong())).thenReturn(false);
        ResponseEntity result = transactionController.addTransaction(new TransactionDto());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void getStatsTest() {
        Stats compare = new Stats(Double.valueOf(3.0), Double.valueOf(3.0),Double.valueOf(3.0),Double.valueOf(3.0), Long.valueOf(3));
        when(transactionService.getStats(Mockito.anyLong())).thenReturn(compare);
        ResponseEntity<Stats> result = transactionController.getStats();
        Stats resultBody = result.getBody();
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(compare.getCount(), resultBody.getCount());
        Assert.assertEquals(compare.getSum(), resultBody.getSum());
    }
}
