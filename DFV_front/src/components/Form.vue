<template>
  <div class="mx-auto p-6 bg-white rounded-lg shadow-md max-w-lg flex gap-2 flex-col ">
    <slot name="body">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
        Coordonnées
      </label>
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="longitude" name="longitude" type="text" v-model="localLongitude"  >
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="latitude" name="latitude" type="text" v-model="localLatitude"  >
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="rayon en mètres" name="rayon" @change="handleupadteLocalisation" type="text" v-model="localRayon">
    </slot>
    <slot name="buttons">
<!--      <DefaultButton type="input" label="Rechercher"/>-->
      <DefaultButton type="button" label="utliser ma position actuel" @click="handleUpdateCurentLocation"/>
<!--      <DefaultButton type='button' label="générer votre rapport" @click="dowloadempPDf()" />-->
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full"  @click="generatePDf(localLongitude, localLatitude, localRayon )">générer votre rapport</button>
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full" :disabled="waitTodownloadPdfValue"  @click="downloadPdfFromApi">télécharger votre rapport</button>

    </slot>
  </div>
</template>
<script setup lang="ts">

import DefaultButton from "./DefaultButton.vue";
import { ref,  watch } from 'vue';



const props = defineProps<{
  longitude?: number,
  latitude?: number,
  rayon?: number
}>()

const emit = defineEmits<{
  (event: 'submitForm', {  } ): void
  (event: 'getLocalisation',{} ): void
}>()
// const request = useRequest()
const localLongitude = ref<number>(props.longitude ?? 0)
const localLatitude = ref<number>(props.latitude ?? 0)
const localRayon = ref<number>(props.rayon ?? 0)

const waitTodownloadPdfValue = ref<boolean>(true)

watch(props, (newValue, oldValue) => {
  localLongitude.value = newValue.longitude
  localLatitude.value = newValue.latitude
  localRayon.value = newValue.rayon

})

async function downloadPdfFromApi() {
  try {
    const response = await fetch('http://localhost:8080/api/transactions/downloadPdf', {
      method: 'GET',
      headers: {
        // Assurez-vous d'inclure les en-têtes nécessaires pour l'authentification ou autres
        'Content-Type': 'application/pdf',
      },
    });

    if (response.ok) {
      const blob = await response.blob();
      const downloadUrl = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = downloadUrl;
      a.download = 'rapport.pdf'; // Nom du fichier à sauvegarder
      document.body.appendChild(a);
      a.click();
      window.URL.revokeObjectURL(downloadUrl);
      a.remove();
    } else {
      console.error('Échec du téléchargement du PDF :', response.statusText);
    }
  } catch (error) {
    console.error('Erreur lors de la requête au serveur:', error);
  }
}

async function generatePDf (longitude: number, latitude: number, rayon: number) {
  console.log('dowloadempPDf')
  waitTodownloadPdfValue.value = true

  const response = await fetch(`http://localhost:8080/api/transactions/generatePdf?latitude=${latitude}&longitude=${longitude}&radius=${rayon}`)
    .then(response => {
      // Vérifie si la requête a réussi
      if (!response.ok) {
        throw new Error('La requête a échoué avec le statut ' + response.status);
      }
      // Parse la réponse en JSON
      return response.json();
    })
    .then(data => {
      console.log('Données reçues:', data);
      // Traitez ici les données reçues
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des données:', error);
    });

  setTimeout(() => {
    waitTodownloadPdfValue.value = false
  }, 3000);
}

const handleGenerateReport = () => {

  // request.downloadPdf()
}
const handleupadteLocalisation = () => {
  emit('submitForm', {longitude:localLongitude.value, latitude:localLatitude.value,rayon: localRayon.value})
}
const handleUpdateCurentLocation = () => {
  emit('getLocalisation')
}
// const handleSubmit = async () => {
//   const response = await fetch('http://localhost:8080/api/transactions/generatePdf?latitude=46.247458&longitude=6.019949&radius=1000')
//     .then(response => {
//       // Vérifie si la requête a réussi
//       if (!response.ok) {
//         throw new Error('La requête a échoué avec le statut ' + response.status);
//       }
//       // Parse la réponse en JSON
//       return response.json();
//     })
//     .then(data => {
//       console.log('Données reçues:', data);
//       // Traitez ici les données reçues
//     })
//     .catch(error => {
//       console.error('Erreur lors de la récupération des données:', error);
//     });
//   emit('submitForm', {longitude:localLongitude.value, latitude:localLatitude.value,rayon: localRayon.value})
//   console.log(localLongitude.value, localLatitude.value, localRayon.value)
// };
</script>
