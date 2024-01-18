
<template>
  <input class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full" :style="props.styles" type="submit" :value="props.label" @click="props.click" v-if="props.type==='input'"/>
  <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full" :style="props.styles"  @click="props.click" v-if="props.type==='button'">{{props.label}}</button>
</template>
<script setup lang="ts">

import {defineProps} from "vue";

const props = defineProps({
  click : Function as () => void,
  label : String,
  styles : String || null,
  type : String || null
})

</script>
