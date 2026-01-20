package com.example.iroribankjosef.domain.transaction;

import com.example.iroribankjosef.domain.account.Account;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Account money is taken FROM
     * - null for deposits
     */
    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    /**
     * Account money is sent TO
     * - null for withdrawals
     */
    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false, updatable = false)
    private Instant timestamp = Instant.now();

    /**
     * JPA only
     */
    protected Transaction() {}

    /* ======================================================
       FACTORY METHODS — ONLY WAY TO CREATE TRANSACTIONS
       ====================================================== */

    public static Transaction deposit(Account toAccount, BigDecimal amount) {
        validate(amount);

        Transaction tx = new Transaction();
        tx.type = TransactionType.DEPOSIT;
        tx.toAccount = toAccount;
        tx.amount = amount;
        return tx;
    }

    public static Transaction withdrawal(Account fromAccount, BigDecimal amount) {
        validate(amount);

        Transaction tx = new Transaction();
        tx.type = TransactionType.WITHDRAWAL;
        tx.fromAccount = fromAccount;
        tx.amount = amount;
        return tx;
    }

    public static Transaction transfer(Account from, Account to, BigDecimal amount) {
        validate(amount);

        Transaction tx = new Transaction();
        tx.type = TransactionType.TRANSFER;
        tx.fromAccount = from;
        tx.toAccount = to;
        tx.amount = amount;
        return tx;
    }

    private static void validate(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }
    }

    /* ======================
       GETTERS ONLY
       ====================== */

    public Long getId() {
        return id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
