package com.tanay.wallet.repository;

import com.tanay.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
