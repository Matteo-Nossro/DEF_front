package com.example.MSCE.DFVProject.service;

import com.example.MSCE.DFVProject.business.TransactionImmobiliere;
import java.util.List;

public interface TransactionImmobiliereService {
    void loadCsvDataToDatabase(String filePath);

    List<TransactionImmobiliere> findAllTransactions();
    // Autres méthodes que vous souhaitez exposer via ce service
}
