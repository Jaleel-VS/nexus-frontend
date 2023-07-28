<template>
    <div>
    <img src="proj_logo.png" />
    </div>

<div class="SupplierPortal">

    <h1 class="text-center">Supplier Portal</h1>
    <router-link to="/SupplierVoucherMetamask">
    <button v-on:click="VerifyVoucher">Verify Voucher</button>
    </router-link>
    <router-link to="/">
      <button class="logout-button">Logout</button>
    </router-link> 
    </div>
</template>

<script>
import { RouterLink } from 'vue-router';
export default {
    name: "SupplierPortal"
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
  .SupplierPortal {
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
  
  .SupplierPortal h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .SupplierPortal button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 1rem;
  }
  .logout-button{
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
    top: 0;
    right: 1%;
  }
  </style>