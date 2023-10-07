<script setup>
import { shallowRef} from 'vue';
import { RouterLink } from 'vue-router';
import IconAngle from './icons/IconAngle.vue';

const props = defineProps({
  menuItems: Array,  // Prop for an array of menu items
});

const isSidebarOpen = shallowRef(false);

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value;
}

</script>

<template>
  <div class="wrapper">
    <aside :vue:is-open="isSidebarOpen">
      <ul class="sidebar-head">
        <li>
          <img src="@/assets/logo.svg" alt="logo" width="32" height="32">
        </li>
        <li>
          <button class="sidebar-toggle" :class="isSidebarOpen ? 'toggle-button' : ''">
            <IconAngle @click="toggleSidebar" />
          </button>
        </li>
      </ul>

      <h4 :transparent="!isSidebarOpen">Menu</h4>

      <ul>
        <!-- Loop through the menuItems array and generate menu items -->
        <li v-for="(menuItem, index) in menuItems" :key="index">
          <router-link :to="menuItem.path">
            <i :class="menuItem.icon"></i>
            <Transition name="fade">
              <span v-show="isSidebarOpen">{{ menuItem.name }}</span>
            </Transition>
          </router-link>
        </li>
      </ul>
    </aside>
  </div>
</template>


<style scoped lang="scss">
@use '@/assets/scss/colors' as clr;
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
$sidebar-width: 4rem;
$toggle-duration: 300ms;
$sidebar-padding-inline-start: 1rem;

aside {
  color: #001f3f;
  background: #E040FB;
  display: flex;
  flex-direction: column;
  height: 100%;
  min-height: 100vh;
  padding-block: 1rem;
  transition: all $toggle-duration;
  width: $sidebar-width;
  font-family: 'Poppins';
}

aside[vue\:is-open=true] {
  width: 3 * $sidebar-width;
}

ul {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  padding-block-end: 1rem;
  color: #fff;
  background: #E040FB;
}

img {
  object-fit: contain;
}

li {
  min-width: fit-content;
  cursor: pointer;
  padding-inline-start: $sidebar-padding-inline-start;

  &:hover {
    color: #fff;
  background: #001f3f;
  }

  & a {
    // border-right: 0.25rem solid white;
    display: flex;
    align-items: center;
    column-gap: 0.75rem;
    position: relative;
    padding-block: 0.5rem;
  }

  a.router-link-exact-active::after {
    content: '';
    position: absolute;
    right: 0;
    width: 0.25rem;
    height: 100%;
    background-color: white;
  }
}

.sidebar-head {
  position: relative;
  // padding-block-end: 4rem;
}

.sidebar-toggle {
  padding-inline-start: $sidebar-padding-inline-start;
}

h4 {
  padding-block-end: 1rem;
  padding-inline-start: $sidebar-padding-inline-start;
  user-select: none;
  pointer-events: none;
  opacity: 0.5;
  text-transform: uppercase;
  font-size: 1.25em;
  // letter-spacing: 0.125ch;
  transition: opacity $toggle-duration;
  color: #001f3f;
}

h4[transparent=true] {
  opacity: 0;
}

button {
  cursor: pointer;
  position: absolute;
  transition-duration: $toggle-duration;
  transition-property: transform, left, top;
  left: 0;
  top: 1rem;
  transform: translateX(0%) translateY(2rem) rotateZ(0deg);
  color: #001f3f;

  &.toggle-button {
    left: 100%;
    top: 0;
    transform: translateX(-100%) translateY(0rem) rotateZ(180deg);
  }
}

.fade-enter-active,
.fade-leave-active {
  transition-property: opacity, transform;
  transition-duration: $toggle-duration;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateX(-100%);
}
</style>
