
<template>
<div class="login-page">
    <div class="login-wrapper">
      <h2>Review Request</h2>
      <div v-if="error" class="error-message">
        <i class="fas fa-exclamation-circle"></i> {{ error }}
      </div>
      <div class="input-wrapper">
        <i class="fas fa-user"></i>
        <label for="infID">Influencer ID:</label>
        <input v-model="infID" type="text" id="infID" placeholder="Influencer ID" />
      </div>
      <div class="input-wrapper">
        <i class="fas fa-box"></i>
        <label for="product">Product:</label>
        <input v-model="product" type="text" id="product" placeholder="Product" />
      </div>
      <div class="input-wrapper">
        <i class="fas fa-industry"></i>
        <label for="supplierID">Supplier ID:</label>
        <input v-model="supplierID" type="text" id="supplierID" placeholder="Supplier ID" />
      </div>
      <div class="input-wrapper">
        <i class="fas fa-calendar"></i>
        <label for="expiryDate">Expiry Date:</label>
        <input v-model="expiryDate" type="text" id="expiryDate" placeholder="Expiry Date" />
      </div>
      <button @click="handleCreateVoucher" class="login-btn">
        <span v-if="loading" class="spinner"></span>
        <span v-else>Create Voucher</span>
      </button>
    </div>
  </div>
  
    
  </template>
  
  
  <script>
  
 
  </script>
  
  <style scoped>
  .centered-login {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
  }
  
  
  
  .login-page {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    width: 100vw;
    font-family: 'Arial', sans-serif;
    background-color: #f7f7f7;
    
  
  }
 
    
  
  .login-wrapper {
    width: 30%;
    max-width: 400px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    gap: 20px;
    opacity: 2;
    background-color: #f7f7f7;
    position: relative;
    z-index: 1; /* Ensure the login form is above the background image */
    
  }
  
  h2 {
    font-size: 2em;
    text-align: center;
    color: #111010;
  }
  
  
  
  
  
  .spinner {
      border: 6px solid #f3f3f3;
      /* Light grey */
      border-top: 6px solid #3498db;
      /* Blue */
      border-radius: 50%;
      width: 10px;
      height: 10px;
      animation: spin 2s linear infinite;
  }
  
  @keyframes spin {
    0% {
      transform: rotate(0deg);
    }
  
    100% {
      transform: rotate(360deg);
    }
  }
  
  .login-btn {
    background-color: #007bff;
    color: #fff;
    font-size: 1.2em;
    border-radius: 5px;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
    justify-content: center;
    display: flex;
  }
  
  input {
    flex: 1;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .error-message {
    color: red;
    text-align: center;
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  /* Responsive Styles */
  @media (max-width: 768px) {
    .login-wrapper {
      width: 90%;
    }
  
    h2 {
      font-size: 1.5em;
    }
  }
  
  
  
  /* Style for the links on hover */
  
  </style>