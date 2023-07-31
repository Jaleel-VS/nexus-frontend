<template>
  <div>
    <img src="proj_logo.png" />
    </div>

  <div class="brandDashboard">
    <h1 class="text-center">Brand Manager Dashboard</h1>
    <h2> Welcome, {{ userDetails.brand_name }}</h2>
      <router-link to="/requestsList">
        <button class="cta-button">Review Requests</button>
      </router-link>
  </div>

</template>

  <script>
  import { RouterLink } from 'vue-router';
  import { useUserStore } from '@/store/user'

  export default {
    name: "brandDashboard",
    setup() {
      const userStore = useUserStore()
      console.log(userStore.details)
      return { userDetails: userStore.details }
    }
  }
  
  </script>

  <style scoped>

img{
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 400px;
            height: auto;

}
  .brandDashboard {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    height: 100vh;
    padding: 2em;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
    color: #333;
  }

  .brandDashboard h1 {
    font-size: 2.5em;
    margin-bottom: 1em;
  }

   .cta-button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .cta-button:hover {
    background-color: #2980b9;
  }
  </style>