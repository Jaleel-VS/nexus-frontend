<template>
  <div class="blue-image-placeholder">
    <img :src="imageSrc" alt="Placeholder" class="placeholder-image" />
    <div class="overlay"></div>
    <div class="content">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    imageSrc: "https://images.unsplash.com/photo-1508808787069-421e7986016e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1587&q=80", // URL of the Unsplash image
  },
};
</script>

<style scoped>
.blue-image-placeholder {
  position: relative;
  background-color: blue;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: transform 0.2s;
  cursor: pointer;
}

.placeholder-image {
  max-width: 100%;
  height: auto;
  display: block;
  border-radius: 10px 10px 0 0;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    to bottom,
    rgba(255, 255, 255, 0) 0%,
    rgba(0, 0, 0, 0.7) 100%
  );
  opacity: 0;
  transition: opacity 0.2s;
}

.content {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 20px;
  color: white;
  text-align: center;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 0 0 10px 10px;
  opacity: 0;
  transition: opacity 0.2s;
}

.blue-image-placeholder:hover {
  transform: scale(1.05);
}

.blue-image-placeholder:hover .overlay,
.blue-image-placeholder:hover .content {
  opacity: 1;
}
</style>
