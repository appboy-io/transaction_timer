package com.transaction.n26.n.service;

import com.transaction.n26.n.model.Stats;
import com.transaction.n26.n.model.Transaction;
import com.transaction.n26.n.model.TransactionDto;
import com.transaction.n26.n.repository.TransactionRepository;
import com.transaction.n26.n.util.TransactionConverter;
import com.transaction.n26.n.util.TransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private static final Long MAX_TRANSACTION_DIFF_MILLI = Long.valueOf(60000);
    public static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean addTransaction(TransactionDto dto, long transactionTime) {
        Transaction transaction = TransactionConverter.transactionDtoToTransaction(dto);
        boolean inTimeTransaction = TransactionValidator.transactionOnTime(transactionTime, dto.getTimestamp());

        if(inTimeTransaction) {
            transactionRepository.save(transaction);
            return true;
        }
        return false;
    }

    @Override
    public Stats getStats(long transactionTime) {
        long maxDiffTime = transactionTime - MAX_TRANSACTION_DIFF_MILLI;
        List<Transaction> transactions = transactionRepository.findByTimestampBetweenQuery(maxDiffTime, transactionTime);
        DoubleSummaryStatistics stats = transactions.stream().mapToDouble(Transaction::getAmount).summaryStatistics();
        return new Stats(stats.getSum(), stats.getAverage(), stats.getMax(), stats.getMin(), stats.getCount());
    }
}
