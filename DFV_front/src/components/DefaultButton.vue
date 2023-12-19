
<template>
  <input class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" :type="props.type" :value="props.label" @click="props.click">
</template>
<script setup lang="ts">

import {defineProps} from "vue";

const props = defineProps({
  click : MouseEvent,
  label : String,
  styles : String || null,
  type:String || null
})
</script>

<style scoped lang="scss">

</style>
