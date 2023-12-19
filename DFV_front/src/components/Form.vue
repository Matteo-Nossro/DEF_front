
<template>
  <form @submit="props.submit">
    <slot name="body">
      <input v-model="longitude" name="longitude">
      <input v-model="latitude" name="latitude">
      <input v-model="rayon" name="rayon">
    </slot>

    <slot name="buttons">
      <DefaultButton label="annuler" />
      <DefaultButton label="submit" type="submit"/>
    </slot>
  </form>
</template>
<script setup lang="ts">

import {ref,defineProps} from "vue";
import DefaultButton from "./DefaultButton.vue";

const props = defineProps({
  submit: Function
})

const longitude = ref(0)
const latitude = ref(0)
const rayon = ref(0)

</script>

<style scoped lang="scss">

</style>
