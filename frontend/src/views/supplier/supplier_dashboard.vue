<script setup>
import { RouterView } from 'vue-router'
import SidebarMenu from '@/views/components/SidebarMenu.vue';
import { ref } from 'vue'

const menuItems = ref([
  { icon: 'fa fa-home fa-2x', name: 'Redeem Voucher', path: '/supplierT' },
 // { icon: 'fa fa-chart-line fa-2x', name: 'Performance metrics', path: '/supplier/supplier_metrics' },
  //{ icon: 'fa fa-home fa-2x', name: 'View Vouchers', path: '/supplier/supplier_viewvoucher'},
  //{ icon: 'fa fa-bell fa-2x', name: 'Change password', path: '/change_password' },
  //{ icon: 'fa fa-envelope fa-2x', name: 'Contact', path: '/contact' },
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