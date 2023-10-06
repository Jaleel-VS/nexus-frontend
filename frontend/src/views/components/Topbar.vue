<script setup>
import { shallowRef } from 'vue';

const props = defineProps({
  menuItems: Array, // Prop for an array of menu items
});

const isSidebarOpen = shallowRef(false);

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value;
}

// Function to handle user logout
function logout() {
  // Implement your logout logic here
}

// Function to navigate to the user's profile
function viewProfile() {
  // Implement your navigation logic here
}
</script>

<template>
  <div class="topbar">
    <div class="menu-container">
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
    <div class="buttons">
      <!-- Buttons for View Profile and Logout -->
      <button @click="viewProfile" class="action-button">View Profile</button>
      <button @click="logout" class="action-button">Logout</button>
    </div>
  </div>
</template>

<style scoped lang="scss">
/* Your existing SCSS styles can remain mostly unchanged */
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
.topbar {
  background-color: #E040FB;
  color: #fff;
  padding: 0.1rem 0;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.menu-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu {
  display: flex;
  gap: 0.5rem;
  list-style: none;
  padding-left: 0;
  margin-top: 1rem;
}

.buttons {
  display: flex;
  gap: 10px;
}

/* Style for the action buttons */
.action-button {
  
  padding: 10px 20px;
  background-color: #001f3f;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 20px;
  font-family: 'Roboto', serif;
}

.action-button:hover {
  opacity: 0.8;
}
</style>
