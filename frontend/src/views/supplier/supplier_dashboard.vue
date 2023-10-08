<script setup>
import { RouterView } from 'vue-router'
import SidebarMenu from '@/views/components/SidebarMenu.vue';
import Navbar from '@/views/components/navbar.vue'; // Changed component name to start with an uppercase letter
import { ref } from 'vue'

const menuItems = ref([
  { icon: 'fa fa-home fa-2x', name: 'Redeem Voucher', path: '/supplier' },
  { icon: 'fa fa-chart-line fa-2x', name: 'Performance metrics', path: '/supplier/supplier_metrics' },
  { icon: 'fa fa-home fa-2x', name: 'View Vouchers', path: '/supplier/supplier_viewvoucher'},
  { icon: 'fa fa-bell fa-2x', name: 'Change password', path: '/change_password' },
  { icon: 'fa fa-envelope fa-2x', name: 'Contact', path: '/contact' },
]);
</script>

<template>
  <div id="root" class="container">
    <Navbar username="John Doe" class="navbar" />
    <div class="content">
      <SidebarMenu :menuItems="menuItems" class="sidebar" />
      <RouterView class="router-view" />
    </div>
  </div>
</template>


<style scoped lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

.container {
  display: flex;
  flex-direction: column;
  font-family: 'Poppins', sans-serif;
  height: 100vh; /* Set the container to fill the entire viewport height */
}

.content {
  display: flex;
  flex: 1; /* Grow and fill the available space */
}

.navbar {
  flex: 0 0 auto; /* Navbar does not grow or shrink, maintains its initial size */
}

.sidebar {
  flex: 0 0; /* Sidebar width */
}

.router-view {
  flex: 1; /* Router view takes up remaining space */
}
</style>