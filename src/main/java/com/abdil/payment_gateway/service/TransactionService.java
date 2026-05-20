package com.abdil.payment_gateway.service;

import com.abdil.payment_gateway.dto.TransactionRequest; // Import DTO baru kita
import com.abdil.payment_gateway.entity.Transaction;
import com.abdil.payment_gateway.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // UBAH FUNGSI INI
    public Transaction createTransaction(TransactionRequest requestData) {
        // 1. Buat cetakan Entity kosong
        Transaction newTransaction = new Transaction();

        // 2. Pindahkan data dari DTO ke Entity
        newTransaction.setMerchantId(requestData.getMerchantId());
        newTransaction.setAmount(requestData.getAmount());

        // 3. Set status default
        newTransaction.setStatus("PENDING");

        // 4. Simpan ke database
        return transactionRepository.save(newTransaction);
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction payTransaction(String id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setStatus("SUCCESS");
            return transactionRepository.save(transaction);
        }
        return null;
    }
}