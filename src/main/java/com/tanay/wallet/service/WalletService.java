package com.tanay.wallet.service;

import com.tanay.wallet.entity.Transaction;
import com.tanay.wallet.entity.Wallet;
import com.tanay.wallet.repository.TransactionRepository;
import com.tanay.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;

    public Wallet createWallet(Wallet wallet) {
        if(wallet.getId()==null){
            walletRepository.save(wallet);

        }else {

            walletRepository.save(wallet);
        }
        return wallet;
    }

    public ResponseEntity<Wallet> getWalletDetails(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent())
            return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> rechargeWallet(Long id,Double amount){
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if (optionalWallet.isPresent()){
            Wallet wallet=optionalWallet.get();
            wallet.setBalance(wallet.getBalance()+amount);
            walletRepository.save(wallet);

            return  new ResponseEntity<>(wallet,HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Wallet> useWalletBalance(Long id,Double amount){
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if (optionalWallet.isPresent()){
            Wallet wallet=optionalWallet.get();

            if(wallet.getBalance()>=amount) {
                wallet.setBalance(wallet.getBalance() - amount);
                walletRepository.save(wallet);
                return new ResponseEntity<>(wallet, HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
