import { ref } from 'vue';
// ça c'est si on générais le pdf en front
// export interface DVF{
//   id: number;
//   idMutation: string;
//   dateMutation: string;
//   natureMutation: string;
//   valeurFonciere: string;
//   adresseNumero: string;
//   adresseSuffixe: string;
//   adresseNomVoie: string;
//   adresseCodeVoie: string;
//   codePostal: string;
//   nomCommune: string;
//   codeDepartement: string;
//   idParcelle: string;
//   longitude: string;
//   latitude: string;
// }
//
//
// export interface DVFResponse {
//   data: DVF[];
// }
//
// async function generateDefPdf(): Promise<DVFResponse> {
//   const response = await fetch('http://localhost:8081/api/transaction/first10');
//   return response.json();
//
//
//
// }

let URL = 'http://localhost:8081/api/transaction/first10';

 async function downloadPdf() {
  try {
    const response = await fetch(URL, {
      method: 'GET'
    });
    if (!response.ok) {
      throw new Error(`Erreur HTTP ! statut: ${response.status}`);
    }
    const blob = await response.blob();
    const downloadUrl = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = downloadUrl;
    link.setAttribute('download', 'Transactions.pdf'); // Nom du fichier PDF à télécharger
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  } catch (error) {
    console.error("Erreur lors du téléchargement du PDF : ", error);
  }
}

export function useRequest() {
  return {
    downloadPdf,
  };
}

