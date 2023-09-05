
<template>
  <div class="yellow">
    <h1> {{ title }}</h1>
  </div>
</template>

<script setup>

import { ref } from 'vue';

const title = ref('Yellow');
</script>

<style scoped lang="scss">
.yellow {
  background-color: yellow;
}
</style>