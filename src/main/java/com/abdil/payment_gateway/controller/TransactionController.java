package com.abdil.payment_gateway.controller;

import com.abdil.payment_gateway.dto.TransactionRequest; // Import DTO
import com.abdil.payment_gateway.entity.Transaction;
import com.abdil.payment_gateway.service.TransactionService;
import jakarta.validation.Valid; // Import library validasi
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // UBAH FUNGSI INI (Tambahkan @Valid dan ubah Transaction menjadi TransactionRequest)
    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}/pay")
    public Transaction payTransaction(@PathVariable String id) {
        return transactionService.payTransaction(id);
    }
}