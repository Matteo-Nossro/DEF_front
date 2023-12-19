package com.example.MSCE.DFVProject.mapper;

import com.example.MSCE.DFVProject.business.TransactionImmobiliere;
import com.example.MSCE.DFVProject.dto.TransactionImmobiliereDto;

public class TransactionImmobiliereMapper {
    public TransactionImmobiliere toEntity(TransactionImmobiliereDto transactionImmobilereDto) {
        TransactionImmobiliere transaction = new TransactionImmobiliere();
        transaction.setId(transactionImmobilereDto.getId());
        transaction.setAdresseNumero(transactionImmobilereDto.getAdresseNumero());
        transaction.setAdresseSuffixe(transactionImmobilereDto.getAdresseSuffixe());
        transaction.setAdresseNomVoie(transactionImmobilereDto.getAdresseNomVoie());
        transaction.setAdresseCodeVoie(transactionImmobilereDto.getAdresseCodeVoie());
        transaction.setCodePostal(transactionImmobilereDto.getCodePostal());
        transaction.setCodeCommune(transactionImmobilereDto.getCodeCommune());
        transaction.setNomCommune(transactionImmobilereDto.getNomCommune());
        transaction.setSurfaceTerrain(transactionImmobilereDto.getSurfaceTerrain());
        transaction.setLongitude(transactionImmobilereDto.getLongitude());
        transaction.setLatitude(transactionImmobilereDto.getLatitude());
        return transaction;
    }

    public TransactionImmobiliereDto toDto(TransactionImmobiliere transaction) {
        TransactionImmobiliereDto transactionDto = new TransactionImmobiliereDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAdresseNumero(transaction.getAdresseNumero());
        transactionDto.setAdresseSuffixe(transaction.getAdresseSuffixe());
        transactionDto.setAdresseNomVoie(transaction.getAdresseNomVoie());
        transactionDto.setAdresseCodeVoie(transaction.getAdresseCodeVoie());
        transactionDto.setCodePostal(transaction.getCodePostal());
        transactionDto.setCodeCommune(transaction.getCodeCommune());
        transactionDto.setNomCommune(transaction.getNomCommune());
        transactionDto.setSurfaceTerrain(transaction.getSurfaceTerrain());
        transactionDto.setLongitude(transaction.getLongitude());
        transactionDto.setLatitude(transaction.getLatitude());
        return transactionDto;
    }
}
