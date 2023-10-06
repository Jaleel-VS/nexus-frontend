<script setup>
import { shallowRef } from 'vue';
import { RouterLink } from 'vue-router';
import IconAngle from './icons/IconAngle.vue';

const props = defineProps({
  menuItems: Array, // Prop for an array of menu items
});

const isSidebarOpen = shallowRef(false);

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value;
}
</script>

<template>
  <div class="topbar">
    <div class="logo-toggle">
      <img src="@/assets/logo.svg" alt="logo" width="32" height="32">
      <button class="sidebar-toggle" :class="isSidebarOpen ? 'toggle-button' : ''" @click="toggleSidebar">
        <IconAngle />
      </button>
    </div>
    <h4 :class="{ 'transparent': !isSidebarOpen }">Menu</h4>
    <ul class="menu">
      <!-- Loop through the menuItems array and generate menu items -->
      <li v-for="(menuItem, index) in menuItems" :key="index">
        <router-link :to="menuItem.path">
          <i :class="menuItem.icon"></i>
          <transition name="fade">
            <span v-show="isSidebarOpen">{{ menuItem.name }}</span>
          </transition>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<style scoped lang="scss">
/* Your existing SCSS styles can remain mostly unchanged */

.topbar {
  background: #E040FB;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  font-family: 'Poppins';
}

.logo-toggle {
  display: flex;
  align-items: center;
  column-gap: 0.5rem;
  cursor: pointer;
}

.menu {
  display: flex;
  gap: 0.5rem;
  list-style: none;
  padding-left: 0;
  margin-top: 1rem;
}

/* Update the media query to adapt to smaller screens */
@media (max-width: 768px) {
  .topbar {
    flex-direction: column;
    align-items: flex-start;
  }

  .logo-toggle {
    margin-bottom: 1rem;
  }
}
</style>

  