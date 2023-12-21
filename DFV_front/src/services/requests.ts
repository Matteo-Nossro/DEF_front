import { ref } from 'vue';

export interface DVF{
  id: number;
  idMutation: string;
  dateMutation: string;
  natureMutation: string;
  valeurFonciere: string;
  adresseNumero: string;
  adresseSuffixe: string;
  adresseNomVoie: string;
  adresseCodeVoie: string;
  codePostal: string;
  nomCommune: string;
  codeDepartement: string;
  idParcelle: string;
  longitude: string;
  latitude: string;
}


export interface DVFResponse {
  data: DVF[];
}

async function generateDefPdf(): Promise<DVFResponse> {
  const response = await fetch('http://localhost:8081/api/transaction/first10');
  return response.json();
}

export function useRequest() {
  return {
    generateDefPdf,
  };
}

