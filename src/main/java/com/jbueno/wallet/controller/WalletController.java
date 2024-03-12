package com.jbueno.wallet.controller;

import com.jbueno.wallet.entities.Wallet;
import com.jbueno.wallet.repository.WalletRepository;
import com.jbueno.wallet.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        Wallet newWallet = walletRepository.save(wallet);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWallet);
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        wallets.forEach(
                w -> w.setIncomes(incomeService.getIncomes(w.getId()))
        );
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        wallet.ifPresent(
                w -> w.setIncomes(incomeService.getIncomes(id))
        );
        return ResponseEntity.ok(wallet.get());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
//        //Wallet updatedWallet = walletRepository.findById(id);
//        //return ResponseEntity.ok(updatedWallet);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        walletRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
