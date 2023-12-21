package com.example.MSCE.DFVProject.service.impl;

import com.example.MSCE.DFVProject.business.TransactionImmobiliere;
import com.example.MSCE.DFVProject.repository.TransactionImmobiliereRepository;
import com.example.MSCE.DFVProject.service.TransactionImmobiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionImmobiliereServiceImpl implements TransactionImmobiliereService {

    private TransactionImmobiliereRepository repository;

    private CsvDataServiceImpl csvDataService;

    public void loadCsvDataToDatabase(String filePath) {
        List<TransactionImmobiliere> transactions = csvDataService.readCsvData(filePath);
        repository.saveAll(transactions);
    }

    @Override
    public List<TransactionImmobiliere> findAllTransactions() {
        return repository.findAll();
    }
    // ... autres méthodes du service
}
