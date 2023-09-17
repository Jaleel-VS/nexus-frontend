<template>
    <div class="home">
      <h1>Welcome to Nexus</h1>
      <p>
        Nexus is an app
      </p>
      <p>
        Mention cool stuff
      </p>
      <router-link to="/login">
        <button class="cta-button">Log In 🧑‍💻</button>
      </router-link>
    </div>
  </template>
  
  <script setup>
  import { RouterLink } from 'vue-router';
  </script>
  
  <style scoped>
  .home {
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
  
  .home h1 {
    font-size: 2.5em;
    margin-bottom: 1em;
  }
  
  .home p {
    margin-bottom: 1em;
    line-height: 1.5;
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
  