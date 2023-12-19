
<template>
<section>
  <h1>Je suis la </h1>
  <Form :submit="()=>console.log('je suis la')"/>
</section>
</template>

<script setup lang="ts">
import Form from "../components/Form.vue";


</script>

<style scoped lang="scss">

</style>
