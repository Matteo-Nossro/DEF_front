package com.example.MSCE.DFVProject.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.MSCE.DFVProject.service.TransactionImmobiliereService;
import com.example.MSCE.DFVProject.business.TransactionImmobiliere;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionImmobiliereController {

    @Autowired
    private TransactionImmobiliereService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionImmobiliere>> getAllTransactions() {
        List<TransactionImmobiliere> transactions = transactionService.findAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Vous pouvez ajouter d'autres méthodes pour gérer différentes requêtes
    // Par exemple, pour rechercher par date, par type de local, etc.
}
