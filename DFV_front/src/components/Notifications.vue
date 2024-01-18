<template>
  <div class="notification " :class="{ Success: props.type === 'success', Error: props.type === 'error' }" v-if="localDisplay">
    <h1 class="title">Accueil</h1>
    <p class="body"></p>
  </div>
</template>

<script setup lang="ts">
import { ref,defineProps,defineEmits } from 'vue';

const props = defineProps({
  title: String,
  body: String,
  type: String,
  display: Boolean,
});

const emits = defineEmits<{
  (event: 'close'): void
}>()

const localDisplay = ref<boolean>(props.display)

setInterval(
  () => {
    localDisplay.value = false
    emits('close')
  },
  3000,
)

</script>

<style scoped lang="scss">
.notification {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 1rem;
  background-color: #fff;
  color: #000;
  z-index: 1000;
  transition: all 0.5s ease;
  opacity: 1;
  transform: translateY(-100%);

  &.Success {
    background-color: #4caf50;
    color: #fff;
  }

  &.Error {
    background-color: #f44336;
    color: #fff;
  }

  .title{
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }

  .body{
    font-size: 1rem;
  }
}

</style>
