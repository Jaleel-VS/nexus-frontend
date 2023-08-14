<template>
    <div>
    <img src="proj_logo.png" />
    </div>

<div class="Influencer_Request_Voucher">

    <h1 class="text-center">Request Voucher</h1>

    <div class="input-container">
    <input type="text" v-model="Name" placeholder="Name" />
  </div>
  <div class="input-container">
    <input type="text" v-model="Username" placeholder="Username" />
  </div>
  <div class="input-container">
    <input type="text" v-model="Brand" placeholder="Brand" />
  </div>
  <div class="input-container">
    <input type="text" v-model="SocialMed" placeholder="Social Media Handle" />
  </div>
  <div class="input-container">
    <input type="text" v-model="Quantity" placeholder="Quantity" />
  </div>
    <button v-on:click="submit" >Submit</button>

    </div>
</template>

<script>
export default {
    name: "Influencer_Request_Voucher"
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
  .Influencer_Request_Voucher {
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
  
  .Influencer_Request_Voucher h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .Influencer_Request_Voucher button {
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

  .input-container {
  margin-bottom: 10px; /* You can adjust the spacing as desired */
}
  .y-button{
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
  

  </style>