package com.example.MSCE.DFVProject.service;

import com.example.MSCE.DFVProject.business.TransactionImmobiliere;

import java.util.List;

public interface CsvDataService {

    List<TransactionImmobiliere> readCsvData(String filePath);

}
