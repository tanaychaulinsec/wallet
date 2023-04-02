package com.tanay.wallet.repository;

import com.tanay.wallet.entity.Transaction;
import com.tanay.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
//    List<Transaction> findByWallet(Wallet wallet);
}