<template>
    <div id="root">
      <SidebarMenu
        :menuItems="menuItems"
       class="sidebar" />
      <RouterView class="router-view" />
    </div>
  </template>
  
<script setup>
import { RouterView } from 'vue-router'
import SidebarMenu from '@/views/components/SidebarMenu.vue';
import { ref } from 'vue'

const menuItems = ref([
  { icon: 'fa fa-home fa-2x', name: 'Redeem Voucher', path: '/bran' },
  { icon:  'fa fa-chart-line fa-2x', name: 'Performance metrics', path: '/yellow' },
  { icon: 'fa fa-bell fa-2x', name: 'Change password', path: '/blue' },
  { icon: 'fa fa-envelope fa-2x', name: 'Contact', path: '/red' },
  // Add more menu items as needed
]);
</script>

<style scoped lang="scss">
#root {
  display: flex;
  flex-direction: row;
}

.router-view {
  flex: 1;
}
</style>
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