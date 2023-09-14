<!-- ToastNotification.vue -->
<template>
    <div class="toast-notification" v-if="show">
      {{ message }}
    </div>
  </template>
  
  <script>
  export default {
    props: ['message'],
    data() {
      return {
        show: false,
      };
    },
    methods: {
      showNotification() {
        this.show = true;
        setTimeout(() => {
          this.show = false;
        }, 3000); // Hide the toast after 3 seconds (adjust as needed)
      },
    },
  };
  </script>
  
  <style scoped>
  /* Add your styling for the toast notification here */
  .toast-notification {
    position: fixed;
    bottom: 20px;
    right: 20px;
    background-color: #333;
    color: #fff;
    padding: 10px 20px;
    border-radius: 5px;
  }
  </style>