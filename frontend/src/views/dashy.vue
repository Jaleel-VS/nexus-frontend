<script>
import Sidebar from '@/views/components/sidebar/Sidebar.vue'
import { sidebarWidth } from '@/views/components/sidebar/state'

export default {
  components: { Sidebar },

  setup() {
    return { sidebarWidth }
  }
}
</script>

<template>
    <div class="dashy">
      <div class="sidebar">
        <Sidebar />
      </div>
      <div class="content">
        <RouterView />
      </div>
    </div>
  </template>
  
  <style scoped>
  .dashy {
    display: flex; /* Use flexbox to create a row layout */
  }
  
  .sidebar {
    width: auto; /* Adjust the width as needed or use a fixed width like "300px" */
    min-width: 0; /* Allow sidebar to shrink if content overflows */
  }
  
  .content {
    flex-grow: 1; /* Allow content to grow and occupy available space */
  }
  </style>
  