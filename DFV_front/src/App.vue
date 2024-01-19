<template>
  <Accueil/>
</template>
<script setup lang="ts">
  import Accueil from './pages/Accueil.vue'
  import {onMounted,ref} from "vue";

  // TODO : remplacer la variable VITE_SOCKET_ENDPOINT par l'url de l'api
  const websocketUrl = 'http://localhost:8081/api/transactions/subscribe';

  const ws = ref<WebSocket | null>  (null);
  const connect = () => {
    ws.value = new WebSocket(websocketUrl);
    console.log('webscoket initalisé')
    ws.value.onmessage = event => {
      const message = JSON.parse(event.data);
      console.log('message du webscoket',message)

      // ToDO: // faire un traitement sur le message reçu
    };

    ws.value.onerror = error => {
      console.error('WebSocket Error:', error);
    };

    ws.value.onclose = event => {
      console.log('WebSocket Closed:', event);
      setTimeout(() => connect(), 5000); // Reconnextion automatique
    };
  };

  onMounted(() => {
    console.log('mounted')
    connect()
  })

</script>

