package com.tanay.wallet.Controller;

import com.tanay.wallet.entity.Wallet;
import com.tanay.wallet.service.ValidationErrorService;
import com.tanay.wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    private ValidationErrorService validationErrorService;

    @PostMapping ("/createWallet")
    public ResponseEntity<Wallet> create(@Valid @RequestBody Wallet wallet) {
        Wallet walletSaved=walletService.createWallet(wallet);
        return new ResponseEntity<Wallet>(walletSaved,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable Long id) {
        return walletService.getWalletDetails(id);
    }
    @PatchMapping("/{id}/deposit")
    public ResponseEntity<?> deposite(@PathVariable Long id, @RequestParam Double amount, BindingResult result) {
        ResponseEntity<?> errors = validationErrorService.validate(result);
        if(errors != null) return errors;
        return walletService.rechargeWallet(id, amount);
    }
    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<Wallet> withdraw(@PathVariable Long id,@RequestParam Double amount,BindingResult result){

        return walletService.useWalletBalance(id, amount);
    }


}
