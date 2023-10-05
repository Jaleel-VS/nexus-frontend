<template>
    <div>
      <Navbar :username="username" />
      <!-- Other content -->
      <h1>👋 Welcome!</h1>

      <p>This is the influencer portal, from here you can make request voucher, view pending requests, and redeem vouchers issued to you!.</p>

        <div class="buttons">
            <button @click="handleRequestVoucher">Request Voucher</button>
            <button @click="handleViewPendingRequests">View Pending Requests</button>
        </div> 
    </div>
  </template>
  
  <script>
  import Navbar from '../components/navbar.vue';
  // vue3-router
  import { useRouter } from 'vue-router'
  // pinia
  import { useUserStore } from '@/store/user'
  
  export default {
      components: {
          Navbar
      },
  
      setup() {
          const router = useRouter()
          const userStore = useUserStore()
  
          const username = userStore.details.username

            const handleRequestVoucher = () => {
                router.push('/influencer/request-voucher');
            }

            const handleViewPendingRequests = () => {
                router.push('/influencer/requests');
            }
  
          return {
              username, handleRequestVoucher, handleViewPendingRequests
          }
      },
  
  };
  </script>

  <style scoped>
  @import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

  h1 {
    font-family: 'Arial', sans-serif;
    font-size: 2em;
    color: #111010;
    margin: 0;
    padding: 20px;
    text-align: center;
  }

  p {
    font-family: 'Arial', sans-serif;
    font-size: 1.2em;
    color: #111010;
    margin: 0;
    padding: 20px;
    text-align: center;
  }

  .buttons {
    display: flex;
    gap: 20px;
    justify-content: center;
    margin-top: 40px;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
    background-color: #007bff;
    color: white;
}

button:hover {
    background-color: #0069d9;
}

  </style>