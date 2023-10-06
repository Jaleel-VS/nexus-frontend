<template>
  <div id="root">
    <TopBar :menuItems="menuItems" 
    class="topbar" />
    <RouterView class="router-view" />
    <!-- Other content of your page goes here -->
  </div>
</template>

<script>
import TopBar from '@/views/components/TopBar.vue';
import { RouterView } from 'vue-router'

export default {
  components: {
    TopBar,
  },
  
  // Other component options
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
#root {
  display: grid;
  grid-template-areas: 'topbar router-view';
  grid-template-columns: auto 1fr;
  font-family: sans-serif;
}

.topbar {
  grid-area: 'sidebar';
}

.router-view {
  grid-area: 'router-view';
  padding: 1rem;
}
</style>

  