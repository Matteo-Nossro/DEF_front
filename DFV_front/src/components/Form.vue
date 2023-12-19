<template>
  <form @submit.prevent="handleSubmit" class="mx-auto p-6 bg-white rounded-lg shadow-md max-w-lg flex gap-2 flex-col ">
    <slot name="body">
      <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
        Coordonnées
      </label>
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="longitude" name="longitude" type="number">
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="latitude" name="latitude" type="number">
      <input class="mx-auto p-6 bg-white rounded-lg max-w-lg border-[1px] border-gray-500  h-[40px] text-black"
             placeholder="rayon en m" name="rayon" type="number">
    </slot>
    <slot name="buttons">
      <DefaultButton label="Rechercher"/>
    </slot>
  </form>
</template>
<script setup lang="ts">

import DefaultButton from "./DefaultButton.vue";

const emit = defineEmits<{
  (event: 'submit', formValue :Record<string, any>[] ): void
}>()
const handleSubmit = () => {
  let results: Record<string, any>[] = []

  let resultTemp: Record<string, any>;

  for (let i = 0; i < event.target.length; i++) {

    if (event.target[i].tagName === 'INPUT' && event.target[i].name !== '') {
      resultTemp = {
        name: event.target[i].name,
        value: event.target[i].value
      }
      results.push(resultTemp)
    }
  }
  emit('submit', results)
};

</script>

<style scoped lang="scss">

</style>
