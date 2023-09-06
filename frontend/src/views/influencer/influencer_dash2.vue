<script setup>
import { RouterView } from 'vue-router'
import SidebarMenu from '@/views/components/SidebarMenu.vue';
import { ref } from 'vue'

const menuItems = ref([
  { icon: 'fa fa-home fa-2x', name: 'Request Voucher', path: '/influencerDash' },
  { icon: 'fa fa-camera-retro fa-2x', name: 'View Requests', path: '/yellow' },
  { icon: 'fa fa-bell fa-2x', name: 'Tasks', path: '/blue' },
  { icon: 'fa fa-envelope fa-2x', name: 'Stats', path: '/red' },
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