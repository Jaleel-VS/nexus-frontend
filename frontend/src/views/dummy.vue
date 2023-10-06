<template>
  <div id="root">
    <TopBar :menuItems="menuItems" 
    class="topbar" />
    <RouterView class="router-view" />
    <!-- Other content of your page goes here -->
  </div>
</template>

<script>
import TopBar from '@/components/TopBar.vue';
import { RouterView } from 'vue-router'

export default {
  components: {
    TopBar,
  },
  data() {
    return {
      menuItems: [
        // Define your menu items here
        {
          path: '/about',
          name: 'About',
          icon: 'fa fa-home fa-2x', // Replace with your icon class
        },
        // Add more menu items as needed
      ],
    };
  },
  // Other component options
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
#root {
  display: grid;
  grid-template-areas: 'sidebar router-view';
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

  