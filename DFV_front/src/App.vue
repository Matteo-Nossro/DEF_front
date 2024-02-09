<template>
  <Accueil/>
</template>
<script setup lang="ts">
  import Accueil from './pages/Accueil.vue'
  import {onMounted,ref} from "vue";
  import { Client, CompatClient, Stomp } from '@stomp/stompjs';

  // TODO : remplacer la variable VITE_SOCKET_ENDPOINT par l'url de l'api
  // const websocketUrl = 'ws://localhost:8080/ws';
  //
  // const ws = ref<WebSocket | null>  (null);
  // const connect = () => {
  //   ws.value = new WebSocket(websocketUrl);
  //   ws.value.onmessage = event => {
  //     const message = JSON.parse(event.data);
  //     console.log('message du webscoket',message)
  //
  //     // TODO: // faire un traitement sur le message reçu
  //   };
  //
  //   ws.value.onopen = () => {
  //     console.log('WebSocket Opened');
  //
  //   };
  //
  //   ws.value.onerror = error => {
  //     console.error('WebSocket Error:', error);
  //   };
  //
  //   ws.value.onclose = event => {
  //     console.log('WebSocket Closed:', event);
  //     setTimeout(() => connect(), 5000); // Reconnextion automatique
  //   };
  //
  // };

  const websocketUrl = 'ws://localhost:8080/ws';
  const client: Ref<CompatClient | null> = ref(null);

  const connect = () => {
    // Créer une nouvelle instance de Stomp client
    client.value = Stomp.over(() => new WebSocket(websocketUrl));

    // Configuration du client pour désactiver les logs de débogage
    client.value.debug = () => {};

    const onConnect = () => {
      console.log('WebSocket Opened');

      // S'abonner à un topic spécifique
      // client.value.subscribe('/topic/pdf', message => {
      //   // Traiter le message reçu
      //   const body = JSON.parse(message.body);
      //   console.log('message du WebSocket', body);
      //
      //   // TODO: faire un traitement sur le message reçu
      // });
      client.value.subscribe('/topic/pdfStatus', function (message) {
        alert("Message: " + message.body);
      });
    };

    const onError = (error) => {
      console.error('WebSocket Error:', error);
      setTimeout(() => connect(), 5000); // Reconnextion automatique
    };

    // Connecter le client avec les callbacks pour onConnect et onError
    client.value.connect({}, onConnect, onError);
  };

  const disconnect = () => {
    if (client.value) {
      client.value.disconnect(() => {
        console.log('WebSocket Closed');
      });
    }
  };

  const sendMessage = (destination, payload) => {
    if (client.value && client.value.connected) {
      client.value.send(destination, {}, JSON.stringify(payload));
      console.log(`Message sent to ${destination}`, payload);
    }
  };


  onMounted(() => {
    console.log('mounted')
    connect()
    sendMessage('/app/message', { content: 'Hello WebSocket' });

  })



</script>

