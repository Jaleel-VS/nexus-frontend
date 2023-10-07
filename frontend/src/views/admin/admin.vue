<script setup>
import { RouterView } from 'vue-router'
import SidebarMenu from '@/views/components/SidebarMenu.vue';
import { ref } from 'vue'

const menuItems = ref([
  { icon: 'fa fa-home fa-2x', name: 'Add Product', path: '/add_product' },
  { icon: 'fa fa-address-card fa-2x', name: 'Remove Product', path: '/remove_product' },
  { icon: 'fa fa-bell fa-2x', name: 'Remove User', path: '/remove_user' },
  { icon: 'fa fa-envelope fa-2x', name: 'Generate Reports', path: '/generate_reports' },
  // Add more menu items as needed
]);
</script>

<template>
  <div id="root">
    <SidebarMenu
      :menuItems="menuItems"
     class="sidebar" />
    <RouterView class="router-view" />
  </div>
</template>


<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
#root {
  display: grid;
  grid-template-areas: 'sidebar router-view';
  grid-template-columns: auto 1fr;
  font-family: sans-serif;
}

.sidebar {
  grid-area: 'sidebar';
}

.router-view {
  grid-area: 'router-view';
  padding: 1rem;
}

</style>