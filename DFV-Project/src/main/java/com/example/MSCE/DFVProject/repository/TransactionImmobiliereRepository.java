package com.example.MSCE.DFVProject.repository;

import com.example.MSCE.DFVProject.business.TransactionImmobiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionImmobiliereRepository extends JpaRepository<TransactionImmobiliere, Long> {

        List<TransactionImmobiliere> findByCodeCommuneAndCodePostal(String codeCommune, String codePostal);
        @Query("SELECT t FROM TransactionImmobiliere t WHERE t.longitude BETWEEN :minLon AND :maxLon AND t.latitude BETWEEN :minLat AND :maxLat")
        List<TransactionImmobiliere> findByLocationWithin(@Param("minLon") Double minLon, @Param("maxLon") Double maxLon, @Param("minLat") Double minLat, @Param("maxLat") Double maxLat);

}
