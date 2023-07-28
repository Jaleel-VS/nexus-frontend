
<template>
  <div>
    <img src="proj_logo.png" />
    </div>

  <div class="InfluencerPortal">
    <h1 class="text-center">Influencer Portal</h1>
      <router-link to="/request_voucher">
        <button class="cta-button">Request Voucher</button>
      </router-link>
      <router-link to="/view_voucher">
        <button class="cta-button">View Voucher</button>
      </router-link>
      <router-link to="/">
        <button class="logout-button">Log out</button>
      </router-link>
  </div>

</template>

  <script setup>
  import { RouterLink } from 'vue-router';
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
  .InfluencerPortal {
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

  .InfluencerPortal h1 {
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
    margin-top: 20px;
  }
  
  .cta-button:hover {
    background-color: #2980b9;
  }
  .logout-button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    position: absolute;
    top: 10px;
    right: 10px;
  }
  
  .logout-button:hover {
    background-color: #2980b9;
  }
  </style>