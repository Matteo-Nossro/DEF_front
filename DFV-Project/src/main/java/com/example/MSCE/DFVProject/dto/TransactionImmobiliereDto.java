package com.example.MSCE.DFVProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionImmobiliereDto {

    private long id;

    private String adresseNumero;
    private String adresseSuffixe;
    private String adresseNomVoie;
    private String adresseCodeVoie;
    private String codePostal;
    private String codeCommune;
    private String nomCommune;
    private String surfaceTerrain;
    private String longitude;
    private String latitude;
}
