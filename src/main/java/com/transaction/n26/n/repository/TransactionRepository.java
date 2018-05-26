package com.transaction.n26.n.repository;

import com.transaction.n26.n.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    @Query("{'timestamp' : {$gt : ?0, $lt : ?1}}")
    public List<Transaction> findByTimestampBetweenQuery(long start, long end);
}
