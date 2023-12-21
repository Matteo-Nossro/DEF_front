package com.example.MSCE.DFVProject.repository;

import com.example.MSCE.DFVProject.business.TransactionImmobiliere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionImmobiliereRepository extends JpaRepository<TransactionImmobiliere, Long> {

        TransactionImmobiliere findByCodeCommuneAndCodePostal(String codeCommune, String codePostal);
}
