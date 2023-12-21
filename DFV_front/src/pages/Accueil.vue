<template>
  <section>
    <Map />
<!--    <div class="form-container">-->
<!--      <Form />-->
<!--    </div>-->
  </section>
</template>

<script setup lang="ts">
// import Form from "../components/Form.vue";
import Map from '../components/Map.vue';


</script>

<style scoped lang="scss">
.form-container{
  position: absolute;
  top: 0;
  left: 0;

  background-color: white;
  z-index: 100;
  padding: 1rem;
  box-shadow: 0 0 10px rgba(0,0,0,0.5);
  overflow-y: scroll;
  overflow-x: hidden;
  scrollbar-width: none;
  -ms-overflow-style: none;
}
</style>
