package com.transaction.n26.n.model;

public class TransactionDto {
    private Double amount;
    private Long timestamp;

    public TransactionDto(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionDto() {}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
