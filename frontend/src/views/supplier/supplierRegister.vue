<template>
    <h1>Supplier Registration</h1>
  <body>
      <div class="container">
              <div class="form-group">
                  <label for="company-name">Company Name:</label>
                  <input type="text" id="company-name" name="company-name" required>
              </div>
              <div class="form-group">
                  <label for="contact-name">Contact Name:</label>
                  <input type="text" id="contact-name" name="contact-name" required>
              </div>
              <div class="form-group">
                  <label for="email">Email:</label>
                  <input type="email" id="email" name="email" required>
              </div>
              <div class="form-group">
                  <label for="password">Password:</label>
                  <input type="password" id="password" name="password" required>
              </div>
              <router-link to="/supplier_dashboard">
                  <button class="sign-in-btn">Apply</button>
              </router-link>
      </div>
  </body>
  </template>
  
  <script>
  import { useRouter } from 'vue-router'
  
  // import on mount
  import { onMounted } from 'vue'
  
  // api
  import { API_ENDPOINT, OTHER_CONST } from '@/config/constants.js';
  
  // import axios
  import axios from 'axios'
  
  
  export default {
      setup() {
          const router = useRouter();
  
          // call api on mount
          onMounted(() => {
              axios.get(API_ENDPOINT)
                  .then(res => {
                      console.log(res.data)
                  })
                  .catch(err => {
                      console.log(err)
                  })
          })
  
  
  
          const displayLoginPage = () => {
              router.push('/login')
          }
  
          return { displayLoginPage }
      }
  };
  </script>
  
  
  <style scoped>
  body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
  }
  
  .container {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  h1 {
      font-size: 1.5em;
      color: #111010;
      margin-bottom: 10px;
      align-items: center;
  }
  
  .form-group {
      margin-bottom: 20px;
  }
  
  label {
      display: block;
      margin-bottom: 5px;
  }
  
  input[type="text"],
  input[type="email"],
  input[type="password"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
  }
  
  button {
      background-color: #007bff;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
  }
  
  button:hover {
      background-color: #0056b3;
  }
  </style>