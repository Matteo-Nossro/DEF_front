<template>
  <form @submit.prevent="handleSubmit" class="mx-auto p-6 bg-white rounded-lg shadow-md max-w-lg flex gap-2 flex-col ">
    <slot name="body">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
        Coordonnées
      </label>
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="longitude" name="longitude" type="text" v-model="localLongitude"  >
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="latitude" name="latitude" type="text" v-model="localLatitude"  >
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="rayon en mètres" name="rayon" type="text" v-model="localRayon">
    </slot>
    <slot name="buttons">
      <DefaultButton type="input" label="Rechercher"/>
      <DefaultButton type="button" label="utliser ma position actuel" :click="handleUpdateCurentLocation"/>
      <DefaultButton type='button' label="générer votre rapport" @click="dowloadempPDf()" />
    </slot>
  </form>
</template>
<script setup lang="ts">

import DefaultButton from "./DefaultButton.vue";
import { ref, defineEmits, defineProps, watch } from 'vue';
import {useRequest} from '../services/requests';



const props = defineProps<{
  longitude?: number,
  latitude?: number,
  rayon?: number
}>()

const emit = defineEmits<{
  (event: 'submitForm', {  } ): void
  (event: 'getLocalisation',{} ): void
}>()
const request = useRequest()
const localLongitude = ref<number>(props.longitude ?? 0)
const localLatitude = ref<number>(props.latitude ?? 0)
const localRayon = ref<number>(props.rayon ?? 0)

watch(props, (newValue, oldValue) => {
  localLongitude.value = newValue.longitude
  localLatitude.value = newValue.latitude
  localRayon.value = newValue.rayon
})

async function dowloadempPDf () {

  const response = await fetch('http://localhost:8080/api/transactions/generatePdf?latitude=46.247458&longitude=6.019949&radius=1000')
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
}

const handleGenerateReport = () => {

  // request.downloadPdf()
}

const handleUpdateCurentLocation = () => {
  emit('getLocalisation')
}
const handleSubmit = async () => {
  const response = await fetch('http://localhost:8080/api/transactions/generatePdf?latitude=46.247458&longitude=6.019949&radius=1000')
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
  emit('submitForm', {longitude:localLongitude.value, latitude:localLatitude.value,rayon: localRayon.value})
  console.log(localLongitude.value, localLatitude.value, localRayon.value)
};
</script>
