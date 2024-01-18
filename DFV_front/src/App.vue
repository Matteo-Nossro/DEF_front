<template>
  <Accueil/>
</template>
<script setup lang="ts">
  import Accueil from './pages/Accueil.vue'
  import { onMounted } from 'vue';

 onMounted(() => {
    const eventSource = new EventSource('\'http://localhost:8081/api/transactions/subscribe');

    eventSource.onmessage = function(event) {
      console.log('Message:', event.data);
      alert('Notification: ' + event.data);

    };
  });
</script>

