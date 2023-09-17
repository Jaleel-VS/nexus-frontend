template>
    <div>
      <button @click="showToast">Show Toast</button>
      <toast-notification :message="notificationMessage" ref="toast"></toast-notification>
    </div>
  </template>
  
  <script>
  import ToastedNotiication from './ToastedNotification.vue';
  
  export default {
    components: {
      ToastNotification,
    },
    data() {
      return {
        notificationMessage: 'This is a toast notification!',
      };
    },
    methods: {
      showToast() {
        this.$refs.toast.showNotification();
      },
    },
  };
  </script>