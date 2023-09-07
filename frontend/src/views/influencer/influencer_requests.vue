<template>
  <div class="carousel">
    <button @click="moveCarousel(-1)" :disabled="isLeftDisabled">
      <i class="fas fa-chevron-left"></i>
    </button>
    <div class="carousel-container">
      <div
        v-for="(item, index) in visibleItems"
        :key="index"
        :class="{ 'carousel-item-selected': selectedIndex === index }"
        @click="selectItem(index)"
      >
        {{ item }}
      </div>
    </div>
    <button @click="moveCarousel(1)" :disabled="isRightDisabled">
      <i class="fas fa-chevron-right"></i>
    </button>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const items = ref(['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5', 'Item 6']);
const selectedIndex = ref(0);

const itemsPerPage = 3;
const currentPage = ref(0);

const visibleItems = computed(() => {
  const start = currentPage.value * itemsPerPage;
  return items.value.slice(start, start + itemsPerPage);
});

const isLeftDisabled = computed(() => currentPage.value === 0);
const isRightDisabled = computed(() => currentPage.value === Math.ceil(items.value.length / itemsPerPage) - 1);

const moveCarousel = (step) => {
  currentPage.value += step;
};

const selectItem = (index) => {
  selectedIndex.value = index;
};
</script>

<style scoped>
.carousel {
  display: flex;
  align-items: center;
}

.carousel-container {
  display: flex;
  overflow: hidden;
  transition: transform 0.3s;
}

.carousel-container > div {
  flex: 0 0 33.33%;
}

.carousel-item-selected {
  background-color: lightblue;
}

button {
  margin: 0 10px;
  background: none;
  border: none;
  cursor: pointer;
  font-size: 1.5rem;
}

button:disabled {
  color: #ccc;
  cursor: not-allowed;
}
</style>
